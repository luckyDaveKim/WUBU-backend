package com.wubu.api.stockvalue.daily.price.dto.res

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import com.wubu.api.stockvalue.daily.price.dto.DailyPriceDto
import java.util.stream.Collectors

data class DailyPriceResDto(
        @JsonValue
        val dailyChart: List<DailyPriceDto>) {

    companion object {
        fun of(dailyPrices: List<DailyPrice>): DailyPriceResDto {
            return DailyPriceResDto(
                    dailyPrices.stream()
                            .map { dailyPrice -> DailyPriceDto.of(dailyPrice) }
                            .collect(Collectors.toList())
            )
        }
    }
}

