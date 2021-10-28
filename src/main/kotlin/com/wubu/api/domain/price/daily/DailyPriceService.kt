package com.wubu.api.domain.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.price.daily.DailyPriceRes
import java.time.LocalDate

interface DailyPriceService {

    fun retrieveDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): DailyPriceRes

    fun retrieveThisWeekPrices(companyCode: CompanyCode, date: LocalDate): DailyPriceRes
}
