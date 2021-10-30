package com.wubu.api.infra.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.domain.volume.daily.DailyVolume
import com.wubu.api.domain.volume.daily.DailyVolumeReader
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DailyVolumeReaderImpl(
    private val dailyVolumeRepository: DailyVolumeRepository
) : DailyVolumeReader {

    override fun getDailyVolumes(companyCode: CompanyCode, pagingReqDto: PagingReqDto): List<DailyVolume> {
        return dailyVolumeRepository.findAllByIdCompanyCodeOrderByIdDateDesc(
            companyCode = companyCode,
            pageable = pagingReqDto.getPageable()
        ).reversed()
    }

    override fun getThisWeekVolumes(companyCode: CompanyCode, date: LocalDate): List<DailyVolume> {
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val startDateOfNextWeek = DateUtil.getStartDateOfNextWeek(date)
        return dailyVolumeRepository.findAllById_CompanyCodeAndId_DateGreaterThanEqualAndId_DateLessThanOrderByIdDateAsc(
            companyCode = companyCode,
            greaterThanEqualDate = startDateOfThisWeek,
            lessThanDate = startDateOfNextWeek
        )
    }
}
