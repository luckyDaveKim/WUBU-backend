package com.wubu.api.interfaces.price.daily

import com.wubu.api.application.price.daily.DailyPriceFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/daily/price")
class DailyPriceController(
    private val dailyPriceFindService: DailyPriceFindService
) {

    @GetMapping(
        "/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findDailyPrices(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @ModelAttribute pagingReqDto: PagingReqDto
    ): PointResDto {
        return dailyPriceFindService.findDailyStockValue(companyCode, pagingReqDto)
    }
}