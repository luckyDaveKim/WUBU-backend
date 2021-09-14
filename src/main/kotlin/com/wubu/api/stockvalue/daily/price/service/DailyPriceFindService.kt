package com.wubu.api.stockvalue.daily.price.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.stockvalue.daily.price.binding.DailyPriceConverter.DailyPriceToPointConverter
import com.wubu.api.stockvalue.daily.price.repository.DailyPriceRepository
import com.wubu.api.stockvalue.daily.service.DailyStockValueFindService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DailyPriceFindService(
        private val dailyPriceRepository: DailyPriceRepository,
        private val dailyPriceToPointConverter: DailyPriceToPointConverter
) : DailyStockValueFindService {

    override fun findDailyStockValue(code: Code, pagingReqDto: PagingReqDto): PointResDto {
        val points = dailyPriceRepository.findAllByIdCodeOrderByIdDateDesc(code, pagingReqDto.getPageable())
                .reversed()
                .map(dailyPriceToPointConverter::convert)
                .toList()

        return PointResDto.of(points)
    }

    override fun findThisWeekStockValue(code: Code, date: LocalDate): PointResDto {
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val points = dailyPriceRepository.findAllByIdCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
                code,
                startDateOfThisWeek)
                .map(dailyPriceToPointConverter::convert)
                .toList()

        return PointResDto.of(points)
    }
}