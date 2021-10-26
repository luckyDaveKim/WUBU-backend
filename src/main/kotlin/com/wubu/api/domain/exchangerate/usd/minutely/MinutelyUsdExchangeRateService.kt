package com.wubu.api.domain.exchangerate.usd.minutely

import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateRes
import java.time.LocalDate

interface MinutelyUsdExchangeRateService {

    fun retrieveMinutelyExchangeRate(date: LocalDate): MinutelyUsdExchangeRateRes
}
