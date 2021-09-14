package com.wubu.api.stockvalue.daily.price.repository

import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.daily.price.entity.DailyPrice
import com.wubu.api.stockvalue.daily.price.entity.DailyPriceId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyPriceRepository : JpaRepository<DailyPrice, DailyPriceId> {
    fun findAllByIdCodeOrderByIdDateDesc(
            code: Code,
            pageable: Pageable): List<DailyPrice>

    fun findAllByIdCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
            code: Code,
            date: LocalDate): List<DailyPrice>
}