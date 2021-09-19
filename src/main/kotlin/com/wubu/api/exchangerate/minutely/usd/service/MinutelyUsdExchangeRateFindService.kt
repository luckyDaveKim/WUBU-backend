package com.wubu.api.exchangerate.minutely.usd.service

import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.exchangerate.minutely.usd.binding.MinutelyUsdExchangeRateConverter.MinutelyUsdExchangeRateToPointConverter
import com.wubu.api.exchangerate.minutely.usd.repository.MinutelyUsdExchangeRateRepository
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
                .toList()

        return PointResDto.of(points)
    }
}
