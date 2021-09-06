package com.wubu.api.price.daily.repository

import com.wubu.api.price.daily.entity.DailyPrice
import com.wubu.api.price.daily.entity.DailyPriceId
import com.wubu.api.price.daily.model.Code
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DailyPriceRepository : JpaRepository<DailyPrice, DailyPriceId> {
    fun findAllByIdCodeOrderByIdDateAsc(code: Code): List<DailyPrice>
}