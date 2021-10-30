package com.wubu.api.domain.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.volume.daily.DailyVolumeRes
import java.time.LocalDate

interface DailyVolumeService {

    fun retrieveDailyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): DailyVolumeRes

    fun retrieveThisWeekVolumes(companyCode: CompanyCode, date: LocalDate): DailyVolumeRes
}
