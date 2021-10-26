package com.wubu.api.application.exchangerate.usd.status

import com.wubu.api.domain.exchangerate.usd.status.UsdExchangeRateStatusService
import com.wubu.api.interfaces.exchangerate.usd.status.UsdExchangeRateStatusRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UsdExchangeRateStatusFacade(
    private val usdExchangeRateStatusService: UsdExchangeRateStatusService
) {

    fun retrieveExchangeRateStatusAtDate(date: LocalDate): UsdExchangeRateStatusRes {
        return usdExchangeRateStatusService.retrieveExchangeRateStatusAtDate(date)
    }
}
