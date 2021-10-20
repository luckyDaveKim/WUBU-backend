package com.wubu.api.domain.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateConverter.DailyUsdExchangeRateToPointConverter
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DailyUsdExchangeRateServiceImpl(
    private val dailyUsdExchangeRateReader: DailyUsdExchangeRateReader,
    private val dailyUsdExchangeRateToPointConverter: DailyUsdExchangeRateToPointConverter
) : DailyUsdExchangeRateService {

    @Transactional
    override fun getDailyExchangeRate(pagingReqDto: PagingReqDto): DailyUsdExchangeRateRes {
        val points = dailyUsdExchangeRateReader.findDailyExchangeRates(pagingReqDto)
            .map(dailyUsdExchangeRateToPointConverter::convert)

        return DailyUsdExchangeRateRes.of(points)
    }
}
