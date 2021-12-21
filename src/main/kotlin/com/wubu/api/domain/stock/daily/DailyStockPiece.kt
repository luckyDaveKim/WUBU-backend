package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.OHLC
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import java.time.LocalDate

class DailyStockPiece(
    val date: LocalDate,
    override val price: OHLC,
    override val volume: Volume
) : StockPiece {
    override val x: Number
        get() = TODO("Not yet implemented")
}
