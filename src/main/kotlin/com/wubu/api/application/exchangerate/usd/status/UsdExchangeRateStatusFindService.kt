package com.wubu.api.application.exchangerate.usd.status

import com.wubu.api.infra.exchangerate.usd.minutely.MinutelyUsdExchangeRateRepository
import com.wubu.api.interfaces.exchangerate.usd.status.UsdExchangeRateStatusDto
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UsdExchangeRateStatusFindService(
    private val minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository
) {
    fun findExchangeRateStatusAtDate(date: LocalDate): UsdExchangeRateStatusDto {
        val curDate = date
        val startCurDateTime = curDate.atStartOfDay()
        val endCurDateTime = curDate.plusDays(1).atStartOfDay()

        val beforeDate = date.minusDays(1)
        val startBeforeDateTime = beforeDate.atStartOfDay()
        val endBeforeDateTime = startCurDateTime

        val curRate =
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = startCurDateTime,
                beforeDateTime = endCurDateTime
            )?.id?.rate ?: throw UsdExchangeRateStatusNotFoundException(startCurDateTime.toLocalDate())
        val beforeRate =
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = startBeforeDateTime,
                beforeDateTime = endBeforeDateTime
            )?.id?.rate ?: throw UsdExchangeRateStatusNotFoundException(startBeforeDateTime.toLocalDate())

        return UsdExchangeRateStatusDto(
            curRate = curRate,
            beforeRate = beforeRate
        )
    }
}
