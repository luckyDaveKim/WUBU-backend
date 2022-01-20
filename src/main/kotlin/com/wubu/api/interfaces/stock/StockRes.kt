package com.wubu.api.interfaces.stock

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.stock.Stock

data class StockRes(
    val companyCode: CompanyCode,
    val companyName: String,
    val stocks: List<StockDto>
) {
    companion object {
        fun of(
            company: Company,
            stocks: List<Stock>
        ): StockRes {
            val stockDtoList = stocks.map { StockDto.of(it) }
            return StockRes(
                companyCode = company.id.companyCode,
                companyName = company.name,
                stocks = stockDtoList
            )
        }
    }
}

data class StockDto(
    val timestamp: Number,
    val open: Number,
    val high: Number,
    val low: Number,
    val close: Number,
    val volume: Number
) {
    companion object {
        fun of(stock: Stock): StockDto {
            return StockDto(
                timestamp = stock.instant.toEpochMilli(),
                open = stock.price.open.value,
                high = stock.price.high.value,
                low = stock.price.low.value,
                close = stock.price.close.value,
                volume = stock.volume.value
            )
        }
    }
}
