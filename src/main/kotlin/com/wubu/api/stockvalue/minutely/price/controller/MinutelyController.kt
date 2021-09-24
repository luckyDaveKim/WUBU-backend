package com.wubu.api.stockvalue.minutely.price.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.stockvalue.minutely.price.service.MinutelyPriceFindService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/minutely/price")
class MinutelyController(
    private val minutelyPriceFindService: MinutelyPriceFindService
) {

    @GetMapping(
        "/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findMinutelyPrices(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @ModelAttribute pagingReqDto: PagingReqDto
    ): PointResDto {
        return minutelyPriceFindService.findMinutelyStockValue(companyCode, pagingReqDto)
    }

    @GetMapping(
        "/{date}/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findMinutelyPricesAtDate(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate
    ): PointResDto {
        return minutelyPriceFindService.findMinutelyStockValueAtDate(companyCode, date)
    }
}
