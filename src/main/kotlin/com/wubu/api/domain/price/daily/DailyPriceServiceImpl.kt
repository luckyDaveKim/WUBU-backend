package com.wubu.api.domain.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.price.daily.DailyPriceConverter.DailyPriceToPointConverter
import com.wubu.api.interfaces.price.daily.DailyPriceRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class DailyPriceServiceImpl(
    private val dailyPriceReader: DailyPriceReader,
    private val dailyPriceToPointConverter: DailyPriceToPointConverter
) : DailyPriceService {

    @Transactional
    override fun retrieveDailyPrices(companyCode: CompanyCode, pagingReqDto: PagingReqDto): DailyPriceRes {
        val points = dailyPriceReader.getDailyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        ).map(dailyPriceToPointConverter::convert)

        return DailyPriceRes.of(points)
    }

    @Transactional
    override fun retrieveThisWeekPrices(companyCode: CompanyCode, date: LocalDate): DailyPriceRes {
        val points = dailyPriceReader.getThisWeekPrices(
            companyCode = companyCode,
            date = date
        ).map(dailyPriceToPointConverter::convert)

        return DailyPriceRes.of(points)
    }
}
