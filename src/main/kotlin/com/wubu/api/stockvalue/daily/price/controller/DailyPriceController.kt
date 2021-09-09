package com.wubu.api.stockvalue.daily.price.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.daily.price.dto.res.DailyPriceResDto
import com.wubu.api.stockvalue.daily.price.service.DailyPriceFindService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/daily/price")
class DailyPriceController(
        private val dailyPriceFindService: DailyPriceFindService
) {

    @GetMapping(
            "/companies/{code}",
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findDailyChart(
            @PathVariable(value = "code") code: Code,
            @ModelAttribute pagingReqDto: PagingReqDto): DailyPriceResDto {
        return dailyPriceFindService.findDailyChart(code, pagingReqDto)
    }

}