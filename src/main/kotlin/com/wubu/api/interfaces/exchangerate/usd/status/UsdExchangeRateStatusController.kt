package com.wubu.api.interfaces.exchangerate.usd.status

import com.wubu.api.application.exchangerate.usd.status.UsdExchangeRateStatusFindService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/status/exchange-rate")
class UsdExchangeRateStatusController(
    private val usdExchangeRateStatusFindService: UsdExchangeRateStatusFindService
) {

    @GetMapping(
        "/{date}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findDailyPrices(
        @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate
    ): UsdExchangeRateStatusDto {
        return usdExchangeRateStatusFindService.findExchangeRateStatusAtDate(date)
    }
}
