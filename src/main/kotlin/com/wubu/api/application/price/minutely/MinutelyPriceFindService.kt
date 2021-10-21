package com.wubu.api.application.price.minutely

import com.wubu.api.application.MinutelyStockValueFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.infra.price.minutely.MinutelyPriceRepository
import com.wubu.api.interfaces.price.minutely.MinutelyPriceConverter.MinutelyPriceToPointConverter
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyPriceFindService(
    private val minutelyPriceRepository: MinutelyPriceRepository,
    private val minutelyPriceToPointConverter: MinutelyPriceToPointConverter
) : MinutelyStockValueFindService {

    fun findMinutelyStockValue(companyCode: CompanyCode, pagingReqDto: PagingReqDto): PointResDto {
        val points =
            minutelyPriceRepository.findAllById_CompanyCodeOrderById_DateTimeDesc(
                companyCode = companyCode,
                pageable = pagingReqDto.getPageable()
            )
                .reversed()
                .map(minutelyPriceToPointConverter::convert)

        return PointResDto.of(points)
    }

    fun findMinutelyStockValueAtDate(companyCode: CompanyCode, date: LocalDate): PointResDto {
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        val points =
            minutelyPriceRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
                .reversed()
                .map(minutelyPriceToPointConverter::convert)

        return PointResDto.of(points)
    }
}
