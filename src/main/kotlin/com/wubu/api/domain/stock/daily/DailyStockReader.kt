package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.stock.Stock

interface DailyStockReader {
    fun getDailyPrices(
        companyCode: CompanyCode,
        pagingReqDto: PagingReqDto
    ): List<Stock>
}
