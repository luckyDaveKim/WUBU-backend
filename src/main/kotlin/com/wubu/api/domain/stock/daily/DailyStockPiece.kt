package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import com.wubu.api.domain.stock.StockPrice
import java.time.ZoneOffset

class DailyStockPiece(
    val id: DailyStockPieceId,
    override val price: StockPrice,
    override val volume: Volume
) : StockPiece {
    override val x: Number
        get() = id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyStockPiece

        if (id != other.id) return false
        if (price != other.price) return false
        if (volume != other.volume) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + volume.hashCode()
        return result
    }
}
