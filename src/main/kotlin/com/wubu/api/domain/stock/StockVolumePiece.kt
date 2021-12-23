package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.stockvalue.Volume

interface StockVolumePiece {
    val x: Number
    val volume: Volume
}
