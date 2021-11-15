package com.wubu.api.infra.exchangerate.usd.minutely

import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateId
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface MinutelyUsdExchangeRateRepository : JpaRepository<MinutelyUsdExchangeRate, MinutelyUsdExchangeRateId> {

    fun findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
        afterEqualDateTime: LocalDateTime,
        beforeDateTime: LocalDateTime
    ): MinutelyUsdExchangeRate?

    fun findAllById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
        afterEqualDateTime: LocalDateTime,
        beforeDateTime: LocalDateTime
    ): List<MinutelyUsdExchangeRate>
}
