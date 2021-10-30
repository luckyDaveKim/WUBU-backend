package com.wubu.api.domain.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.volume.daily.DailyVolumeConverter.DailyVolumeToPointConverter
import com.wubu.api.interfaces.volume.daily.DailyVolumeRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DailyVolumeServiceImpl(
    private val dailyVolumeReader: DailyVolumeReader,
    private val dailyVolumeToPointConverter: DailyVolumeToPointConverter
) : DailyVolumeService {

    override fun retrieveDailyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): DailyVolumeRes {
        val points = dailyVolumeReader.getDailyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        ).map(dailyVolumeToPointConverter::convert)

        return DailyVolumeRes.of(points)
    }

    override fun retrieveThisWeekVolumes(companyCode: CompanyCode, date: LocalDate): DailyVolumeRes {
        val points = dailyVolumeReader.getThisWeekVolumes(
            companyCode = companyCode,
            date = date
        ).map(dailyVolumeToPointConverter::convert)

        return DailyVolumeRes.of(points)
    }
}
