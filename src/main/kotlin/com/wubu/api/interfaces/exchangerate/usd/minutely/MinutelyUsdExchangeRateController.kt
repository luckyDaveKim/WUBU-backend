package com.wubu.api.interfaces.exchangerate.usd.minutely

import com.wubu.api.application.exchangerate.usd.minutely.MinutelyUsdExchangeRateFindService
import com.wubu.api.common.web.dto.PointResDto
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
@RequestMapping("/api/minutely/exchange-rate")
class MinutelyUsdExchangeRateController(
    private val minutelyUsdExchangeRateFindService: MinutelyUsdExchangeRateFindService
) {

    @GetMapping(
        "/{date}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findDailyPrices(
        @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate
    ): PointResDto {
        return minutelyUsdExchangeRateFindService.findMinutelyExchangeRateAtDate(date)
    }
}
