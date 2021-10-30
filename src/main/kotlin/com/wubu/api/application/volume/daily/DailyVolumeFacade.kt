package com.wubu.api.application.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.volume.daily.DailyVolumeService
import com.wubu.api.interfaces.volume.daily.DailyVolumeRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DailyVolumeFacade(
    private val dailyVolumeService: DailyVolumeService
) {

    fun retrieveDailyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): DailyVolumeRes {
        return dailyVolumeService.retrieveDailyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )
    }

    fun retrieveThisWeekVolumes(companyCode: CompanyCode, date: LocalDate = LocalDate.now()): DailyVolumeRes {
        return dailyVolumeService.retrieveThisWeekVolumes(
            companyCode = companyCode,
            date = date
        )
    }
}
