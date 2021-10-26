package com.wubu.api.application.exchangerate.usd.minutely

import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateService
import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyUsdExchangeRateFacade(
    private val minutelyUsdExchangeRateService: MinutelyUsdExchangeRateService
) {
    fun retrieveMinutelyExchangeRateAtDate(date: LocalDate): MinutelyUsdExchangeRateRes {
        return minutelyUsdExchangeRateService.retrieveMinutelyExchangeRate(date)
    }
}
