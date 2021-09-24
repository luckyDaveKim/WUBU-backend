package com.wubu.api.stockvalue.daily.volume.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.stockvalue.daily.service.DailyStockValueFindService
import com.wubu.api.stockvalue.daily.volume.binding.DailyVolumeConverter.DailyVolumeToPointConverter
import com.wubu.api.stockvalue.daily.volume.repository.DailyVolumeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DailyVolumeFindService(
    private val dailyVolumeRepository: DailyVolumeRepository,
    private val dailyVolumeToPointConverter: DailyVolumeToPointConverter
) : DailyStockValueFindService {

    override fun findDailyStockValue(companyCode: CompanyCode, pagingReqDto: PagingReqDto): PointResDto {
        val points =
            dailyVolumeRepository.findAllByIdCompanyCodeOrderByIdDateDesc(companyCode, pagingReqDto.getPageable())
                .reversed()
                .map(dailyVolumeToPointConverter::convert)
                .toList()

        return PointResDto.of(points)
    }

    override fun findThisWeekStockValue(companyCode: CompanyCode, date: LocalDate): PointResDto {
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val points = dailyVolumeRepository.findAllByIdCompanyCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
            companyCode,
            startDateOfThisWeek
        )
            .map(dailyVolumeToPointConverter::convert)
            .toList()

        return PointResDto.of(points)
    }
}
