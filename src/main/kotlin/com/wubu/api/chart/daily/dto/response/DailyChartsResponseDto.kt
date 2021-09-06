package com.wubu.api.chart.daily.dto.response

import com.wubu.api.price.daily.entity.DailyPrice
import com.wubu.api.price.daily.model.Price
import java.time.ZoneId
import java.util.stream.Collectors

data class DailyChartsResponseDto(val dailyChart: List<DailyChartDto>) {

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
            val timestamp: Long,
            val open: Price,
            val high: Price,
            val low: Price,
            val close: Price
    ) {

        companion object {
            private val zoneId = ZoneId.systemDefault()

            fun of(dailyPrice: DailyPrice): DailyChartDto {
                return DailyChartDto(
                        dailyPrice.id.date.atStartOfDay().atZone(zoneId).toEpochSecond(),
                        dailyPrice.open,
                        dailyPrice.high,
                        dailyPrice.low,
                        dailyPrice.close
                )
            }
        }
    }

}

