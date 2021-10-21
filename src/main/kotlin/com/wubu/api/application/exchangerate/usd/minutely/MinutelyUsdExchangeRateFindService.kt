package com.wubu.api.application.exchangerate.usd.minutely

import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.infra.exchangerate.usd.minutely.MinutelyUsdExchangeRateRepository
import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateConverter.MinutelyUsdExchangeRateToPointConverter
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyUsdExchangeRateFindService(
    private val minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository,
    private val minutelyUsdExchangeRateToPointConverter: MinutelyUsdExchangeRateToPointConverter
) {
    fun findMinutelyExchangeRateAtDate(date: LocalDate): PointResDto {
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        val points =
            minutelyUsdExchangeRateRepository.findAllById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
                .reversed()
                .map(minutelyUsdExchangeRateToPointConverter::convert)

        return PointResDto.of(points)
    }
}
