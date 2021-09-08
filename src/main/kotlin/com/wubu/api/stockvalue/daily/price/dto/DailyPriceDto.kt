package com.wubu.api.stockvalue.daily.price.dto

import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import java.time.ZoneOffset

data class DailyPriceDto(
        val x: Long,
        val y: Price,
        val open: Price,
        val high: Price,
        val low: Price,
        val close: Price
) {

    companion object {
        fun of(dailyPrice: DailyPrice): DailyPriceDto {
            return DailyPriceDto(
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