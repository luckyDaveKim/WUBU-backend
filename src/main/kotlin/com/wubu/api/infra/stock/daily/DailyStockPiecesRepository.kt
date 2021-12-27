package com.wubu.api.infra.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.stock.daily.DailyStockPiece
import com.wubu.api.domain.stock.daily.DailyStockPieceId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DailyStockPiecesRepository : JpaRepository<DailyStockPiece, DailyStockPieceId> {
    fun findAllById_CompanyCodeOrderById_DateDesc(
        companyCode: CompanyCode,
        pageable: Pageable
    ): List<DailyStockPiece>
}
