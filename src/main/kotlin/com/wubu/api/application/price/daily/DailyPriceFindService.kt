package com.wubu.api.application.price.daily

import com.wubu.api.application.DailyStockValueFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.infra.price.daily.DailyPriceRepository
import com.wubu.api.interfaces.price.daily.DailyPriceConverter.DailyPriceToPointConverter
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DailyPriceFindService(
    private val dailyPriceRepository: DailyPriceRepository,
    private val dailyPriceToPointConverter: DailyPriceToPointConverter
) : DailyStockValueFindService {

    override fun findDailyStockValue(companyCode: CompanyCode, pagingReqDto: PagingReqDto): PointResDto {
        val points =
            dailyPriceRepository.findAllByIdCompanyCodeOrderByIdDateDesc(companyCode, pagingReqDto.getPageable())
                .reversed()
                .map(dailyPriceToPointConverter::convert)
                .toList()

        return PointResDto.of(points)
    }

    override fun findThisWeekStockValue(companyCode: CompanyCode, date: LocalDate): PointResDto {
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val points = dailyPriceRepository.findAllByIdCompanyCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
            companyCode,
            startDateOfThisWeek
        )
            .map(dailyPriceToPointConverter::convert)
            .toList()

        return PointResDto.of(points)
    }
}
