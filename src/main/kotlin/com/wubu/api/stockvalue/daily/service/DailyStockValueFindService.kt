package com.wubu.api.stockvalue.daily.service

import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.daily.price.dto.res.DailyPriceResDto
import com.wubu.api.stockvalue.service.StockValueFindService

interface DailyStockValueFindService : StockValueFindService {
    fun findThisWeekValue(code: Code): DailyPriceResDto
}