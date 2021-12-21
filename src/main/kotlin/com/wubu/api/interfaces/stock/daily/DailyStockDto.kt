package com.wubu.api.interfaces.stock.daily

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.stock.StockPiece

class DailyStockDto(stockPieces: List<StockPiece>) {

    val prices: List<Point>
        get() {
            TODO()
        }
    val volumes: List<Point>
        get() {
            TODO()
        }
}
