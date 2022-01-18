package com.wubu.api.interfaces.stock.daily

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.stock.Stock

class DailyStockDto(
    stocks: List<Stock>
) {

    val prices: List<Point> = stocks.map { Point.ofPrice(it) }
    val volumes: List<Point> = stocks.map { Point.ofVolume(it) }
}
