package com.wubu.api.interfaces.stock.daily

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.stock.StockPiece

class DailyStockDto(
    stockPieces: List<StockPiece>
) {

    val prices: List<Point> = stockPieces.map { Point.ofPrice(it) }
    val volumes: List<Point> = stockPieces.map { Point.ofVolume(it) }
}
