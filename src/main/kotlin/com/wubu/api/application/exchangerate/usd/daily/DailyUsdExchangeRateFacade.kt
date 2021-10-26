package com.wubu.api.application.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRateService
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateRes
import org.springframework.stereotype.Service

@Service
class DailyUsdExchangeRateFacade(
    private val dailyUsdExchangeRateService: DailyUsdExchangeRateService
) {

    fun retrieveDailyExchangeRate(pagingReqDto: PagingReqDto): DailyUsdExchangeRateRes {
        return dailyUsdExchangeRateService.retrieveDailyExchangeRate(pagingReqDto)
    }
}
