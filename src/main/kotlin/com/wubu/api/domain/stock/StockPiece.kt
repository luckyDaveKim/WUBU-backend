package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.OHLC
import com.wubu.api.common.web.model.stockvalue.Volume

interface StockPiece {
    val x: Number
    val price: OHLC
    val volume: Volume
}
