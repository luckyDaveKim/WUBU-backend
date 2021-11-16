package com.wubu.api.infra.volume.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.volume.daily.DailyVolume
import com.wubu.api.domain.volume.daily.DailyVolumeId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface DailyVolumeRepository : JpaRepository<DailyVolume, DailyVolumeId> {

    fun findAllByIdCompanyCodeOrderByIdDateDesc(
        companyCode: CompanyCode,
        pageable: Pageable
    ): List<DailyVolume>

    fun findAllById_CompanyCodeAndId_DateGreaterThanEqualAndId_DateLessThanOrderByIdDateAsc(
        companyCode: CompanyCode,
        greaterThanEqualDate: LocalDate,
        lessThanDate: LocalDate
    ): List<DailyVolume>
}
