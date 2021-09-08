package com.wubu.api.stockvalue.daily.repository

import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import com.wubu.api.stockvalue.daily.entity.DailyPriceId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DailyPriceRepository : JpaRepository<DailyPrice, DailyPriceId> {
    fun findAllByIdCodeOrderByIdDateAsc(
            code: Code,
            pageable: Pageable): List<DailyPrice>
}