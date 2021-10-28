package com.wubu.api.domain.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import java.time.LocalDate

interface DailyPriceReader {

    fun getDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<DailyPrice>

    fun getThisWeekPrices(companyCode: CompanyCode, date: LocalDate): List<DailyPrice>
}
