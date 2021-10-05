package com.wubu.api.interfaces.volume.minutely

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.volume.minutely.MinutelyVolume
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
