package com.wubu.api.application.volume.daily

import com.wubu.api.application.DailyStockValueFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.infra.volume.daily.DailyVolumeRepository
import com.wubu.api.interfaces.volume.daily.DailyVolumeConverter.DailyVolumeToPointConverter
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

        return PointResDto.of(points)
    }

    override fun findThisWeekStockValue(companyCode: CompanyCode, date: LocalDate): PointResDto {
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val points = dailyVolumeRepository.findAllByIdCompanyCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
            companyCode,
            startDateOfThisWeek
        )
            .map(dailyVolumeToPointConverter::convert)

        return PointResDto.of(points)
    }
}
