package com.wubu.api.domain.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import java.time.LocalDate

interface MinutelyPriceReader {

    fun getMinutelyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<MinutelyPrice>

    fun getMinutelyPricesAtDate(companyCode: CompanyCode, date: LocalDate): List<MinutelyPrice>
}
