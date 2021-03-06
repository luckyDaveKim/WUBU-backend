package com.wubu.api.interfaces.exchangerate.usd.daily

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRate
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZoneOffset

class DailyUsdExchangeRateConverter {

    @Component
    class DailyUsdExchangeRateToPointConverter : Converter<DailyUsdExchangeRate, Point> {
        override fun convert(source: DailyUsdExchangeRate): Point {
            return Point(
                x = source.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                y = source.id.rate.value
            )
        }
    }
}
