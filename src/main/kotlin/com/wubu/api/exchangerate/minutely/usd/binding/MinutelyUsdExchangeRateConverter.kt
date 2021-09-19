package com.wubu.api.exchangerate.minutely.usd.binding

import com.wubu.api.common.web.model.Point
import com.wubu.api.exchangerate.minutely.usd.entity.MinutelyUsdExchangeRate
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZoneOffset

class MinutelyUsdExchangeRateConverter {

    @Component
    class MinutelyUsdExchangeRateToPointConverter : Converter<MinutelyUsdExchangeRate, Point> {
        override fun convert(source: MinutelyUsdExchangeRate): Point {
            return Point(
                x = source.id.dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                y = source.id.rate.value
            )
        }
    }
}
