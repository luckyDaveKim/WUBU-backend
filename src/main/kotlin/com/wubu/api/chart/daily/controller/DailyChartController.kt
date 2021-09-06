package com.wubu.api.chart.daily.controller

import com.wubu.api.chart.daily.dto.response.DailyChartsResponseDto
import com.wubu.api.chart.daily.service.DailyChartService
import com.wubu.api.price.daily.model.Code
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
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findDailyChart(
            @PathVariable(value = "code") code: Code
    ): DailyChartsResponseDto {
        return dailyChartService.findDailyChart(code)
    }

}