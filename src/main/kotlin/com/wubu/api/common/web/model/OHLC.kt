package com.wubu.api.common.web.model

import com.wubu.api.common.web.model.stockvalue.Price

interface OHLC {
    val open: Price
    val high: Price
    val low: Price
    val close: Price
}
