package com.wubu.api.domain.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto

interface DailyUsdExchangeRateReader {

    fun getDailyExchangeRates(pagingReqDto: PagingReqDto): List<DailyUsdExchangeRate>
}
