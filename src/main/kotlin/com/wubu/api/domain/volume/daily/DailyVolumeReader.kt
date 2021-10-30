package com.wubu.api.domain.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import java.time.LocalDate

interface DailyVolumeReader {

    fun getDailyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<DailyVolume>

    fun getThisWeekVolumes(companyCode: CompanyCode, date: LocalDate): List<DailyVolume>
}
