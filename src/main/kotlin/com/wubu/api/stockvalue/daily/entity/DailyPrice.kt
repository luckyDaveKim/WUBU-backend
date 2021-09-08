package com.wubu.api.stockvalue.daily.entity

import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Price.PriceConverter
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.common.web.model.stockvalue.Volume.VolumeConverter
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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyPrice

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}