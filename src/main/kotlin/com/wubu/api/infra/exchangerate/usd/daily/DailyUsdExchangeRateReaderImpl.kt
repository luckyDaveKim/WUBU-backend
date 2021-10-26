package com.wubu.api.infra.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRateReader
import org.springframework.stereotype.Component

@Component
class DailyUsdExchangeRateReaderImpl(
    private val dailyUsdExchangeRateRepository: DailyUsdExchangeRateRepository
) : DailyUsdExchangeRateReader {

    override fun getDailyExchangeRates(pagingReqDto: PagingReqDto): List<DailyUsdExchangeRate> {
        return dailyUsdExchangeRateRepository.findAllByOrderById_DateDesc(pagingReqDto.getPageable())
            .reversed()
    }
}
