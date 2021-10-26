package com.wubu.api.infra.exchangerate.usd.minutely

import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateReader
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class MinutelyUsdExchangeRateReaderImpl(
    private val minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository
) : MinutelyUsdExchangeRateReader {

    override fun getMinutelyExchangeRates(date: LocalDate): List<MinutelyUsdExchangeRate> {
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        return minutelyUsdExchangeRateRepository.findAllById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
            afterEqualDateTime = afterEqualDateTime,
            beforeDateTime = beforeDateTime
        ).reversed()
    }
}
