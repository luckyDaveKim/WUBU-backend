package com.wubu.api.domain.exchangerate.usd.minutely

import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateConverter.MinutelyUsdExchangeRateToPointConverter
import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MinutelyUsdExchangeRateServiceImpl(
    private val minutelyUsdExchangeRateReader: MinutelyUsdExchangeRateReader,
    private val minutelyUsdExchangeRateToPointConverter: MinutelyUsdExchangeRateToPointConverter
) : MinutelyUsdExchangeRateService {

    @Transactional
    override fun getMinutelyExchangeRate(date: LocalDate): MinutelyUsdExchangeRateRes {
        val points = minutelyUsdExchangeRateReader.findMinutelyExchangeRates(date)
            .map(minutelyUsdExchangeRateToPointConverter::convert)

        return MinutelyUsdExchangeRateRes.of(points)
    }
}
