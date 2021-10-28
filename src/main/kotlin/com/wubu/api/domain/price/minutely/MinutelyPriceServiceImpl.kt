package com.wubu.api.domain.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.price.minutely.MinutelyPriceConverter.MinutelyPriceToPointConverter
import com.wubu.api.interfaces.price.minutely.MinutelyPriceRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MinutelyPriceServiceImpl(
    private val minutelyPriceReader: MinutelyPriceReader,
    private val minutelyPriceToPointConverter: MinutelyPriceToPointConverter
) : MinutelyPriceService {

    @Transactional
    override fun retrieveMinutelyPrice(companyCode: CompanyCode, pagingReqDto: PagingReqDto): MinutelyPriceRes {
        val points = minutelyPriceReader.getMinutelyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        ).map(minutelyPriceToPointConverter::convert)

        return MinutelyPriceRes.of(points)
    }

    override fun retrieveMinutelyStockPrice(companyCode: CompanyCode, date: LocalDate): MinutelyPriceRes {
        val points = minutelyPriceReader.getMinutelyPricesAtDate(
            companyCode = companyCode,
            date = date
        ).map(minutelyPriceToPointConverter::convert)

        return MinutelyPriceRes.of(points)
    }
}
