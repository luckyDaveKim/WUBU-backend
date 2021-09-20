package com.wubu.api.exchangerate.daily.usd.repository

import com.wubu.api.exchangerate.daily.usd.entity.DailyUsdExchangeRate
import com.wubu.api.exchangerate.daily.usd.entity.DailyUsdExchangeRateId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DailyUsdExchangeRateRepository : JpaRepository<DailyUsdExchangeRate, DailyUsdExchangeRateId> {
    fun findAllByOrderById_DateDesc(
        pageable: Pageable
    ): List<DailyUsdExchangeRate>
}
