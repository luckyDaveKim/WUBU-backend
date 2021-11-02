package com.wubu.api.domain.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MinutelyVolumeServiceImpl(
    private val minutelyVolumeReader: MinutelyVolumeReader,
    private val minutelyVolumeToPointConverter: MinutelyVolumeToPointConverter
) : MinutelyVolumeService {

    @Transactional
    override fun retrieveMinutelyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): MinutelyVolumeRes {
        val points = minutelyVolumeReader.getMinutelyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        ).map(minutelyVolumeToPointConverter::convert)

        return MinutelyVolumeRes.of(points)
    }

    @Transactional
    override fun retrieveMinutelyVolumesAtDate(companyCode: CompanyCode, date: LocalDate): MinutelyVolumeRes {
        val points = minutelyVolumeReader.getMinutelyVolumesAtDate(
            companyCode = companyCode,
            date = date
        ).map(minutelyVolumeToPointConverter::convert)

        return MinutelyVolumeRes.of(points)
    }
}
