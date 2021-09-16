package com.wubu.api.stockvalue.daily.volume.binding

import com.wubu.api.common.web.model.Point
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolume
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZoneOffset

class DailyVolumeConverter {

    @Component
    class DailyVolumeToPointConverter : Converter<DailyVolume, Point> {
        override fun convert(source: DailyVolume): Point {
            return Point(
                    x = source.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                    y = source.volume.value
            )
        }
    }

}