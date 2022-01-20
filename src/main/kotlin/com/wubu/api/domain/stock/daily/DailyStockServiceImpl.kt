package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.CompanyReader
import com.wubu.api.interfaces.stock.StockRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DailyStockServiceImpl(
    private val companyReader: CompanyReader,
    private val dailyStockReader: DailyStockReader
) : DailyStockService {
    @Transactional
    override fun retrieve(
        companyCode: CompanyCode,
        pagingReqDto: PagingReqDto
    ): StockRes {
        val company = companyReader.getCompanyByCode(companyCode)

        val stocks = dailyStockReader.getDailyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        return StockRes.of(
            company = company,
            stocks = stocks
        )
    }
}
