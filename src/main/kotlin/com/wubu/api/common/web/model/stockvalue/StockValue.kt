package com.wubu.api.common.web.model.stockvalue

import com.wubu.api.common.web.model.SingleValue

abstract class StockValue(
    value: Long
) : SingleValue<Long>(value)
