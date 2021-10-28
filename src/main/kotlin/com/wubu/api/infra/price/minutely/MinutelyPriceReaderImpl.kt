package com.wubu.api.infra.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.price.minutely.MinutelyPrice
import com.wubu.api.domain.price.minutely.MinutelyPriceReader
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class MinutelyPriceReaderImpl(
    private val minutelyPriceRepository: MinutelyPriceRepository
) : MinutelyPriceReader {

    override fun getMinutelyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<MinutelyPrice> {
        return minutelyPriceRepository.findAllById_CompanyCodeOrderById_DateTimeDesc(
            companyCode = companyCode,
            pageable = pagingReqDto.getPageable()
        ).reversed()
    }

    override fun getMinutelyPricesAtDate(companyCode: CompanyCode, date: LocalDate): List<MinutelyPrice> {
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()
        return minutelyPriceRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeAsc(
            companyCode = companyCode,
            afterEqualDateTime = afterEqualDateTime,
            beforeDateTime = beforeDateTime
        )
    }
}
