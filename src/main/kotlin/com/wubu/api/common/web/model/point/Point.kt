package com.wubu.api.common.web.model.point

import com.wubu.api.common.web.model.stockvalue.StockValue

interface Point<E : StockValue> {

    val point: List<E>

}