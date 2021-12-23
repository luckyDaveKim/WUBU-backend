package com.wubu.api.common.web.model

import com.wubu.api.domain.stock.StockPricePiece
import com.wubu.api.domain.stock.StockVolumePiece

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
        fun ofPrice(stockPricePiece: StockPricePiece): Point {
            return Point(
                x = stockPricePiece.x,
                y = stockPricePiece.price.close.value,
                open = stockPricePiece.price.open.value,
                high = stockPricePiece.price.high.value,
                low = stockPricePiece.price.low.value,
                close = stockPricePiece.price.close.value
            )
        }

        fun ofVolume(stockVolumePiece: StockVolumePiece): Point {
            return Point(
                x = stockVolumePiece.x,
                y = stockVolumePiece.volume.value
            )
        }
    }
}
