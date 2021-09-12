package com.wubu.api.common.web.model.point

import com.wubu.api.common.web.model.stockvalue.StockValue

interface PiecePoint<T : StockValue> : Point<T> {

    val value: T

    override val point: List<T>
        get() = listOf(value)

}