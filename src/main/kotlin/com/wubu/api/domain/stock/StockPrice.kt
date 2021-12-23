package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.OHLC
import com.wubu.api.common.web.model.stockvalue.Price

data class StockPrice(
    override val open: Price,
    override val high: Price,
    override val low: Price,
    override val close: Price
) : OHLC
