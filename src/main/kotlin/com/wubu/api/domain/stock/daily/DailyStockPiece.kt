package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import com.wubu.api.domain.stock.StockPrice
import java.time.ZoneOffset

data class DailyStockPiece(
    val id: DailyStockPieceId,
    override val price: StockPrice,
    override val volume: Volume
) : StockPiece {
    override val x: Number
        get() = id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
}
