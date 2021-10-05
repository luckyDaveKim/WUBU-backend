package com.wubu.api.application.volume.minutely

import com.wubu.api.application.MinutelyStackedStockValueFindService
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.volume.minutely.MinutelyVolume
import com.wubu.api.infra.volume.minutely.MinutelyVolumeRepository
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyStackedVolumeFindService(
    private val minutelyVolumeRepository: MinutelyVolumeRepository,
    private val minutelyVolumeToPointConverter: MinutelyVolumeToPointConverter
) : MinutelyStackedStockValueFindService {

    fun findMinutelyStackedStockValueAtDate(companyCode: CompanyCode, date: LocalDate): PointResDto {
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        val points =
            minutelyVolumeRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
                .reversed()
                .stream()
                .collect(MinutelyVolume.toStacked())
                .map(minutelyVolumeToPointConverter::convert)
                .toList()

        return PointResDto.of(points)
    }
}
