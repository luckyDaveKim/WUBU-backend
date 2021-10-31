package com.wubu.api.infra.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.volume.minutely.MinutelyVolume
import com.wubu.api.domain.volume.minutely.MinutelyVolumeReader
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class MinutelyVolumeReaderImpl(
    private val minutelyVolumeRepository: MinutelyVolumeRepository
) : MinutelyVolumeReader {

    override fun getMinutelyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<MinutelyVolume> {
        return minutelyVolumeRepository.findAllById_CompanyCodeOrderById_DateTimeDesc(
            companyCode = companyCode,
            pageable = pagingReqDto.getPageable()
        ).reversed()
    }

    override fun getMinutelyVolumesAtDate(companyCode: CompanyCode, date: LocalDate): List<MinutelyVolume> {
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()
        return minutelyVolumeRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeAsc(
            companyCode = companyCode,
            afterEqualDateTime = afterEqualDateTime,
            beforeDateTime = beforeDateTime
        )
    }
}
