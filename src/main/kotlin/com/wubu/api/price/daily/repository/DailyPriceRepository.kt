package com.wubu.api.price.daily.repository

import com.wubu.api.price.daily.entity.DailyPrice
import com.wubu.api.price.daily.entity.DailyPriceKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DailyPriceRepository : JpaRepository<DailyPrice, DailyPriceKey> {
    fun findAllByIdCodeOrderByIdDateAsc(code: String): List<DailyPrice>
}