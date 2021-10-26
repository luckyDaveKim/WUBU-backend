package com.wubu.api.domain.exchangerate.usd.status

import com.wubu.api.interfaces.exchangerate.usd.status.UsdExchangeRateStatusRes
import java.time.LocalDate

interface UsdExchangeRateStatusService {

    fun retrieveExchangeRateStatusAtDate(date: LocalDate): UsdExchangeRateStatusRes
}
