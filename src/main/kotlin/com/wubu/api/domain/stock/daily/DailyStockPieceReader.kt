package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.stock.StockPricePiece

interface DailyStockPieceReader {
    fun getDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<StockPricePiece>
}
