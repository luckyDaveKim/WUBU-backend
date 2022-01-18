package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.stockvalue.Price
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class OHLC(
    @Column(name = "open", nullable = false)
    @Convert(converter = Price.PriceConverter::class)
    val open: Price,

    @Column(name = "high", nullable = false)
    @Convert(converter = Price.PriceConverter::class)
    val high: Price,

    @Column(name = "low", nullable = false)
    @Convert(converter = Price.PriceConverter::class)
    val low: Price,

    @Column(name = "close", nullable = false)
    @Convert(converter = Price.PriceConverter::class)
    val close: Price
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OHLC

        if (open != other.open) return false
        if (high != other.high) return false
        if (low != other.low) return false
        if (close != other.close) return false

        return true
    }

    override fun hashCode(): Int {
        var result = open.hashCode()
        result = 31 * result + high.hashCode()
        result = 31 * result + low.hashCode()
        result = 31 * result + close.hashCode()
        return result
    }
}
