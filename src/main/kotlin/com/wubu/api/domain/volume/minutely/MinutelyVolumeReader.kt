package com.wubu.api.domain.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import java.time.LocalDate

interface MinutelyVolumeReader {

    fun getMinutelyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<MinutelyVolume>

    fun getMinutelyVolumesAtDate(companyCode: CompanyCode, date: LocalDate): List<MinutelyVolume>
}
