package com.wubu.api.exchangerate.daily.usd.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.exchangerate.daily.usd.service.DailyUsdExchangeRateFindService
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
    private val dailyUsdExchangeRateFindService: DailyUsdExchangeRateFindService
) {

    @GetMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findDailyPrices(
        @ModelAttribute pagingReqDto: PagingReqDto
    ): PointResDto {
        return dailyUsdExchangeRateFindService.findDailyExchangeRate(pagingReqDto)
    }
}
