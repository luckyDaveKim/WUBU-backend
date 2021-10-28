package com.wubu.api.interfaces.price.minutely

import com.wubu.api.application.price.minutely.MinutelyPriceFacade
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
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
class MinutelyPriceController(
    private val minutelyPriceFacade: MinutelyPriceFacade
) {

    @GetMapping(
        "/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findMinutelyPrices(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @ModelAttribute pagingReqDto: PagingReqDto
    ): MinutelyPriceRes {
        return minutelyPriceFacade.retrieveMinutelyPrices(companyCode, pagingReqDto)
    }

    @GetMapping(
        "/{date}/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findMinutelyPricesAtDate(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate
    ): MinutelyPriceRes {
        return minutelyPriceFacade.retrieveMinutelyPricesAtDate(companyCode, date)
    }
}
