package com.wubu.api.infra.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.stock.Stock
import com.wubu.api.domain.stock.daily.DailyStockReader
import org.springframework.stereotype.Component

@Component
class DailyStockReaderImpl(
    private val dailyStockPiecesRepository: DailyStockPiecesRepository
) : DailyStockReader {
    override fun getDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<Stock> {
        return dailyStockPiecesRepository.findAllById_CompanyCodeOrderById_DateDesc(
            companyCode = companyCode,
            pageable = pagingReqDto.getPageable()
        ).reversed()
    }
}
