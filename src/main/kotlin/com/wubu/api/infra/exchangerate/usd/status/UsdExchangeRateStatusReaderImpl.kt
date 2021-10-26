package com.wubu.api.infra.exchangerate.usd.status

import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.status.UsdExchangeRateStatusReader
import com.wubu.api.infra.exchangerate.usd.minutely.MinutelyUsdExchangeRateRepository
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UsdExchangeRateStatusReaderImpl(
    private val minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository
) : UsdExchangeRateStatusReader {

    override fun getLastExchangeRateAtDate(date: LocalDate): MinutelyUsdExchangeRate? {
        val startDateTime = date.atStartOfDay()
        val endDateTime = date.plusDays(1).atStartOfDay()

        return minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
            afterEqualDateTime = startDateTime,
            beforeDateTime = endDateTime
        )
    }
}
