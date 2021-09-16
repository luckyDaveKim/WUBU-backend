package com.wubu.api.stockvalue.daily.price.binding

import com.wubu.api.common.web.model.Point
import com.wubu.api.stockvalue.daily.price.entity.DailyPrice
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZoneOffset

class DailyPriceConverter {

    @Component
    class DailyPriceToPointConverter : Converter<DailyPrice, Point> {
        override fun convert(source: DailyPrice): Point {
            return Point(
                x = source.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                y = source.close.value,
                open = source.open.value,
                high = source.high.value,
                low = source.low.value,
                close = source.close.value
            )
        }
    }
}
