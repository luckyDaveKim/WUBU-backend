package com.wubu.api.chart.daily.dto.response

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.price.daily.entity.DailyPrice
import com.wubu.api.price.daily.model.Price
import java.time.ZoneOffset
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

    data class DailyChartDto(
            val x: Long,
            val y: Price,
            val open: Price,
            val high: Price,
            val low: Price,
            val close: Price
    ) {

        companion object {
            fun of(dailyPrice: DailyPrice): DailyChartDto {
                return DailyChartDto(
                        dailyPrice.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                        dailyPrice.close,
                        dailyPrice.open,
                        dailyPrice.high,
                        dailyPrice.low,
                        dailyPrice.close
                )
            }
        }
    }

}

