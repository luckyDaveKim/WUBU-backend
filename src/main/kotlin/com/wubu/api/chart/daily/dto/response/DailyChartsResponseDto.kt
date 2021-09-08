package com.wubu.api.chart.daily.dto.response

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.chart.daily.dto.DailyChartDto
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import java.util.stream.Collectors

data class DailyChartsResponseDto(
        @JsonValue
        val dailyChart: List<DailyChartDto>) {

    companion object {
        fun of(dailyPrices: List<DailyPrice>): DailyChartsResponseDto {
            return DailyChartsResponseDto(
                    dailyPrices.stream()
                            .map { dailyPrice -> DailyChartDto.of(dailyPrice) }
                            .collect(Collectors.toList())
            )
        }
    }
}

