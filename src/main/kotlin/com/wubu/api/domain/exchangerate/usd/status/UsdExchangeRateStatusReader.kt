package com.wubu.api.domain.exchangerate.usd.status

import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import java.time.LocalDate

interface UsdExchangeRateStatusReader {

    fun getLastExchangeRateAtDate(date: LocalDate): MinutelyUsdExchangeRate?
}
