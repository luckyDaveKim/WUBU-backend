package com.wubu.api.stockvalue.minutely.price.service

import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.stockvalue.minutely.price.binding.MinutelyPriceConverter.MinutelyPriceToPointConverter
import com.wubu.api.stockvalue.minutely.price.repository.MinutelyPriceRepository
import com.wubu.api.stockvalue.minutely.service.MinutelyStockValueFindService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyPriceFindService(
    private val minutelyPriceRepository: MinutelyPriceRepository,
    private val minutelyPriceToPointConverter: MinutelyPriceToPointConverter
) : MinutelyStockValueFindService {
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
                .toList()

        return PointResDto.of(points)
    }
}
