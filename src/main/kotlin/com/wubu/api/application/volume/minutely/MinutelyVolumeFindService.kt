package com.wubu.api.application.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.infra.volume.minutely.MinutelyVolumeRepository
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyVolumeFindService(
    private val minutelyVolumeRepository: MinutelyVolumeRepository,
    private val minutelyVolumeToPointConverter: MinutelyVolumeToPointConverter
) {

    fun findMinutelyStockValue(companyCode: CompanyCode, pagingReqDto: PagingReqDto): PointResDto {
        val points =
            minutelyVolumeRepository.findAllById_CompanyCodeOrderById_DateTimeDesc(
                companyCode = companyCode,
                pageable = pagingReqDto.getPageable()
            )
                .reversed()
                .map(minutelyVolumeToPointConverter::convert)

        return PointResDto.of(points)
    }

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

        return PointResDto.of(points)
    }
}
