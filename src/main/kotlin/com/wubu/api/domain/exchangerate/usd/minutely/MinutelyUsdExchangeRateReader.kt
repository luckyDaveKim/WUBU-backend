package com.wubu.api.domain.exchangerate.usd.minutely

import java.time.LocalDate

interface MinutelyUsdExchangeRateReader {

    fun getMinutelyExchangeRates(date: LocalDate): List<MinutelyUsdExchangeRate>
}
