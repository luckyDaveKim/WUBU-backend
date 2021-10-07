package com.wubu.api.infra.volume.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.volume.daily.DailyVolume
import com.wubu.api.domain.volume.daily.DailyVolumeId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyVolumeRepository : JpaRepository<DailyVolume, DailyVolumeId> {
    fun findAllByIdCompanyCodeOrderByIdDateDesc(
        companyCode: CompanyCode,
        pageable: Pageable
    ): List<DailyVolume>

    fun findAllByIdCompanyCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
        companyCode: CompanyCode,
        date: LocalDate
    ): List<DailyVolume>
}