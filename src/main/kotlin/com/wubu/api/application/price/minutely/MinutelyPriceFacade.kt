package com.wubu.api.application.price.minutely

import com.wubu.api.application.MinutelyStockValueFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.price.minutely.MinutelyPriceService
import com.wubu.api.interfaces.price.minutely.MinutelyPriceRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyPriceFacade(
    private val minutelyPriceService: MinutelyPriceService
) : MinutelyStockValueFindService {

    fun retrieveMinutelyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): MinutelyPriceRes {
        return minutelyPriceService.retrieveMinutelyPrice(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )
    }

    fun retrieveMinutelyPricesAtDate(companyCode: CompanyCode, date: LocalDate): MinutelyPriceRes {
        return minutelyPriceService.retrieveMinutelyStockPrice(
            companyCode = companyCode,
            date = date
        )
    }
}
