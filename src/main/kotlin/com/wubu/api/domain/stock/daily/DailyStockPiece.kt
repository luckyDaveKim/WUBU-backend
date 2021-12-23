package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.OHLC
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import java.time.ZoneOffset

data class DailyStockPiece(
    val id: DailyStockPieceId,
    override val price: OHLC,
    override val volume: Volume
) : StockPiece {
    override val x: Number
        get() = id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
}
