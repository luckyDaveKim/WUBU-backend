package com.wubu.api.domain.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.price.minutely.MinutelyPriceRes
import java.time.LocalDate

interface MinutelyPriceService {

    fun retrieveMinutelyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): MinutelyPriceRes

    fun retrieveMinutelyPricesAtDate(companyCode: CompanyCode, date: LocalDate): MinutelyPriceRes
}
