package com.wubu.api.application.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.price.daily.DailyPriceService
import com.wubu.api.interfaces.price.daily.DailyPriceRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DailyPriceFacade(
    private val dailyPriceService: DailyPriceService
) {

    fun retrieveDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): DailyPriceRes {
        return dailyPriceService.retrieveDailyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )
    }

    fun retrieveThisWeekPrices(companyCode: CompanyCode, date: LocalDate = LocalDate.now()): DailyPriceRes {
        return dailyPriceService.retrieveThisWeekPrices(
            companyCode = companyCode,
            date = date
        )
    }
}
