package com.wubu.api.domain.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeRes
import java.time.LocalDate

interface MinutelyVolumeService {

    fun retrieveMinutelyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): MinutelyVolumeRes

    fun retrieveMinutelyVolumesAtDate(companyCode: CompanyCode, date: LocalDate): MinutelyVolumeRes
}
