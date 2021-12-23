package com.wubu.api.infra.stock.daily

import com.wubu.api.domain.stock.daily.DailyStockPiece
import com.wubu.api.domain.stock.daily.DailyStockPieceId
import org.springframework.data.jpa.repository.JpaRepository

interface DailyStockPiecesRepository : JpaRepository<DailyStockPiece, DailyStockPieceId>
