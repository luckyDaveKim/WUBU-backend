package com.wubu.api.stockvalue.minutely.volume.binding

import com.wubu.api.common.web.model.Point
import com.wubu.api.stockvalue.minutely.volume.entity.MinutelyVolume
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZoneOffset

class MinutelyVolumeConverter {

    @Component
    class MinutelyVolumeToPointConverter : Converter<MinutelyVolume, Point> {
        override fun convert(source: MinutelyVolume): Point {
            return Point(
                x = source.id.dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                y = source.volume.value
            )
        }
    }
}
