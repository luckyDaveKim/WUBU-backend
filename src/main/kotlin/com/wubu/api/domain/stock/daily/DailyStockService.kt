package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.stock.StockRes

interface DailyStockService {
    fun retrieve(
        companyCode: CompanyCode,
        pagingReqDto: PagingReqDto
    ): StockRes
}
