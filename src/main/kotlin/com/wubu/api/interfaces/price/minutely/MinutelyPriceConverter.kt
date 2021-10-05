package com.wubu.api.interfaces.price.minutely

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.price.minutely.MinutelyPrice
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZoneOffset

class MinutelyPriceConverter {

    @Component
    class MinutelyPriceToPointConverter : Converter<MinutelyPrice, Point> {
        override fun convert(source: MinutelyPrice): Point {
            return Point(
                x = source.id.dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                y = source.close.value,
                open = source.open.value,
                high = source.high.value,
                low = source.low.value,
                close = source.close.value
            )
        }
    }
}
