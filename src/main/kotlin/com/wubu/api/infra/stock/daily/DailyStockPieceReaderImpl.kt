package com.wubu.api.infra.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.stock.StockPricePiece
import com.wubu.api.domain.stock.daily.DailyStockPieceReader
import org.springframework.stereotype.Component

@Component
class DailyStockPieceReaderImpl : DailyStockPieceReader {
    override fun getDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<StockPricePiece> {
        TODO("Not yet implemented")
    }
}
