package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.OHLC

interface StockPricePiece {
    val x: Number
    val price: OHLC
}
