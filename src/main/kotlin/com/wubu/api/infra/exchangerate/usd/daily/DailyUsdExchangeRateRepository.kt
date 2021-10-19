package com.wubu.api.infra.exchangerate.usd.daily

import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRateId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DailyUsdExchangeRateRepository : JpaRepository<DailyUsdExchangeRate, DailyUsdExchangeRateId> {

    fun findAllByOrderById_DateDesc(
        pageable: Pageable
    ): List<DailyUsdExchangeRate>
}
