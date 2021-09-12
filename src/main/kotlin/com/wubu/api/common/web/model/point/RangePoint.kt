package com.wubu.api.common.web.model.point

import com.wubu.api.common.web.model.stockvalue.StockValue

interface RangePoint<T : StockValue> : Point<T> {

    val open: T
    val high: T
    val low: T
    val close: T

    override val point: List<T>
        get() = listOf(open, high, low, close)

}