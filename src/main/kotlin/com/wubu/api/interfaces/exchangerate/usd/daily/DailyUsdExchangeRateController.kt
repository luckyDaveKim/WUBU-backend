package com.wubu.api.interfaces.exchangerate.usd.daily

import com.wubu.api.application.exchangerate.usd.daily.DailyUsdExchangeRateFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
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
