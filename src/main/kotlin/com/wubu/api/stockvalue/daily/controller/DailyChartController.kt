package com.wubu.api.stockvalue.daily.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.daily.dto.response.DailyChartsResponseDto
import com.wubu.api.stockvalue.daily.service.DailyChartService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/charts/daily")
class DailyChartController(
        private val dailyChartService: DailyChartService
) {

    @GetMapping(
            "/code/{code}",
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findDailyChart(
            @PathVariable(value = "code") code: Code,
            @ModelAttribute pagingReqDto: PagingReqDto): DailyChartsResponseDto {
        return dailyChartService.findDailyChart(code, pagingReqDto)
    }

}