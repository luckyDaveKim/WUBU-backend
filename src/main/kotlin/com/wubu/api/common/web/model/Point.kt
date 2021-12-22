package com.wubu.api.common.web.model

import com.wubu.api.domain.stock.StockPiece

data class Point(
    val x: Number = 0,
    val y: Number = 0,
    val z: Number = 0,
    val open: Number = 0,
    val high: Number = 0,
    val low: Number = 0,
    val close: Number = 0
) {
    companion object {
        fun ofPrice(stockPiece: StockPiece): Point {
            return Point(
                x = stockPiece.x,
                y = stockPiece.price.close.value,
                open = stockPiece.price.open.value,
                high = stockPiece.price.high.value,
                low = stockPiece.price.low.value,
                close = stockPiece.price.close.value
            )
        }

        fun ofVolume(stockPiece: StockPiece): Point {
            return Point(
                x = stockPiece.x,
                y = stockPiece.volume.value
            )
        }
    }
}
