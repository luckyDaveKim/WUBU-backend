package com.wubu.api.interfaces.exchangerate.usd.minutely

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
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
