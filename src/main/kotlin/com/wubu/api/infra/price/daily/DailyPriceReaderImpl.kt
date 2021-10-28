package com.wubu.api.infra.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.domain.price.daily.DailyPrice
import com.wubu.api.domain.price.daily.DailyPriceReader
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DailyPriceReaderImpl(
    private val dailyPriceRepository: DailyPriceRepository
) : DailyPriceReader {

    override fun getDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<DailyPrice> {
        return dailyPriceRepository.findAllByIdCompanyCodeOrderByIdDateDesc(
            companyCode = companyCode,
            pageable = pagingReqDto.getPageable()
        ).reversed()
    }

    override fun getThisWeekPrices(companyCode: CompanyCode, date: LocalDate): List<DailyPrice> {
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val startDateOfNextWeek = DateUtil.getStartDateOfNextWeek(date)
        return dailyPriceRepository.findAllByIdCompanyCodeAndId_DateGreaterThanEqualAndId_DateLessThanOrderByIdDateAsc(
            companyCode = companyCode,
            greaterThanEqualDate = startDateOfThisWeek,
            lessThanDate = startDateOfNextWeek
        )
    }
}
