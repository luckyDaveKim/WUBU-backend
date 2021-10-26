package com.wubu.api.domain.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateRes

interface DailyUsdExchangeRateService {

    fun retrieveDailyExchangeRate(pagingReqDto: PagingReqDto): DailyUsdExchangeRateRes
}
