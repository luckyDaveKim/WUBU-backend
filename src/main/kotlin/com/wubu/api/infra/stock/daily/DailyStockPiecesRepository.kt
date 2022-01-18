package com.wubu.api.infra.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.stock.daily.DailyStock
import com.wubu.api.domain.stock.daily.DailyStockId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DailyStockPiecesRepository : JpaRepository<DailyStock, DailyStockId> {
    fun findAllById_CompanyCodeOrderById_DateDesc(
        companyCode: CompanyCode,
        pageable: Pageable
    ): List<DailyStock>
}
