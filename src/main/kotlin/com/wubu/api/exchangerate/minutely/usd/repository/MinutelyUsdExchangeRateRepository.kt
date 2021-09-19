package com.wubu.api.exchangerate.minutely.usd.repository

import com.wubu.api.exchangerate.minutely.usd.entity.MinutelyUsdExchangeRate
import com.wubu.api.exchangerate.minutely.usd.entity.MinutelyUsdExchangeRateId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MinutelyUsdExchangeRateRepository : JpaRepository<MinutelyUsdExchangeRate, MinutelyUsdExchangeRateId> {
    fun findAllById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
        afterEqualDateTime: LocalDateTime,
        beforeDateTime: LocalDateTime
    ): List<MinutelyUsdExchangeRate>
}
