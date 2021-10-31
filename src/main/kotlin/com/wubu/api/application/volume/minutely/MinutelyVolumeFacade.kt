package com.wubu.api.application.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.volume.minutely.MinutelyVolumeService
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MinutelyVolumeFacade(
    private val minutelyVolumeService: MinutelyVolumeService
) {

    fun retrieveMinutelyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): MinutelyVolumeRes {
        return minutelyVolumeService.retrieveMinutelyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )
    }

    fun retrieveMinutelyVolumesAtDate(companyCode: CompanyCode, date: LocalDate = LocalDate.now()): MinutelyVolumeRes {
        return minutelyVolumeService.retrieveMinutelyVolumesAtDate(
            companyCode = companyCode,
            date = date
        )
    }
}
