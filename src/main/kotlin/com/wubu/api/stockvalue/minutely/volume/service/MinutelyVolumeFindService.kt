package com.wubu.api.stockvalue.minutely.volume.service

import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.stockvalue.minutely.volume.binding.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import com.wubu.api.stockvalue.minutely.volume.repository.MinutelyVolumeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyVolumeFindService(
    private val minutelyVolumeRepository: MinutelyVolumeRepository,
    private val minutelyVolumeToPointConverter: MinutelyVolumeToPointConverter
) {

    fun findMinutelyStockValueAtDate(companyCode: CompanyCode, date: LocalDate): PointResDto {
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        val points =
            minutelyVolumeRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
                .reversed()
                .map(minutelyVolumeToPointConverter::convert)
                .toList()

        return PointResDto.of(points)
    }
}
