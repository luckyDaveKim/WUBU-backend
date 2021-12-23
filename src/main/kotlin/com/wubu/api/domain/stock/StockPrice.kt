package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.OHLC
import com.wubu.api.common.web.model.stockvalue.Price

class StockPrice(
    override val open: Price,
    override val high: Price,
    override val low: Price,
    override val close: Price
) : OHLC {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StockPrice

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
