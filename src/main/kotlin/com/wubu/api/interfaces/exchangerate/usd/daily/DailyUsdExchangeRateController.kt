package com.wubu.api.interfaces.exchangerate.usd.daily

import com.wubu.api.application.exchangerate.usd.daily.DailyUsdExchangeRateFacade
import com.wubu.api.common.web.dto.PagingReqDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/daily/exchange-rate")
class DailyUsdExchangeRateController(
    private val dailyUsdExchangeRateFacade: DailyUsdExchangeRateFacade
) {

    @GetMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findDailyPrices(
        @ModelAttribute pagingReqDto: PagingReqDto
    ): DailyUsdExchangeRateRes {
        return dailyUsdExchangeRateFacade.retrieveDailyExchangeRate(pagingReqDto)
    }
}
