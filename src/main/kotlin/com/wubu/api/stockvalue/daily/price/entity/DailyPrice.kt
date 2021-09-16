package com.wubu.api.stockvalue.daily.price.entity

import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Price.PriceConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

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
    var close: Price
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
