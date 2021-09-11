package com.wubu.api.stockvalue.daily.price.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.stockvalue.daily.price.dto.res.DailyPriceResDto
import com.wubu.api.stockvalue.daily.repository.DailyPriceRepository
import com.wubu.api.stockvalue.daily.service.DailyStockValueFindService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DailyPriceFindService(
        private val dailyPriceRepository: DailyPriceRepository
) : DailyStockValueFindService {

    fun findDailyChart(code: Code, pagingReqDto: PagingReqDto): DailyPriceResDto {
        val dailyPrices = dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(code, pagingReqDto.getPageable())
        return DailyPriceResDto.of(dailyPrices)
    }

    override fun findThisWeekValue(code: Code, date: LocalDate): DailyPriceResDto {
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val thisWeekDailyPrice = dailyPriceRepository.findAllByIdCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
                code,
                startDateOfThisWeek
        )
        return DailyPriceResDto.of(thisWeekDailyPrice)
    }
}