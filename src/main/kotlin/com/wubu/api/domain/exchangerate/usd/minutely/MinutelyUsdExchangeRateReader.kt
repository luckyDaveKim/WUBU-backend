package com.wubu.api.domain.exchangerate.usd.minutely

import java.time.LocalDate

interface MinutelyUsdExchangeRateReader {

    fun findMinutelyExchangeRates(date: LocalDate): List<MinutelyUsdExchangeRate>
}
