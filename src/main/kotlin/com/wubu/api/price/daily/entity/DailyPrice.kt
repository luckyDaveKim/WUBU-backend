package com.wubu.api.price.daily.entity

import com.wubu.api.price.daily.model.Price
import com.wubu.api.price.daily.model.Price.PriceConverter
import com.wubu.api.price.daily.model.Volume
import com.wubu.api.price.daily.model.Volume.VolumeConverter
import javax.persistence.*

@Entity
@Table(name = "daily_price")
class DailyPrice(
        @EmbeddedId
        var id: DailyPriceId,

        @Column(name = "open", nullable = false)
        @Convert(converter = PriceConverter::class)
        var open: Price,

        @Column(name = "high", nullable = false)
        @Convert(converter = PriceConverter::class)
        var high: Price,

        @Column(name = "low", nullable = false)
        @Convert(converter = PriceConverter::class)
        var low: Price,

        @Column(name = "close", nullable = false)
        @Convert(converter = PriceConverter::class)
        var close: Price,

        @Column(name = "diff", nullable = false)
        var diff: Long,

        @Column(name = "volume", nullable = false)
        @Convert(converter = VolumeConverter::class)
        var volume: Volume
)