package com.wubu.api.infra.volume.minutely

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.volume.minutely.MinutelyVolume
import com.wubu.api.domain.volume.minutely.MinutelyVolumeId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface MinutelyVolumeRepository : JpaRepository<MinutelyVolume, MinutelyVolumeId> {

    fun findAllById_CompanyCodeOrderById_DateTimeDesc(
        companyCode: CompanyCode,
        pageable: Pageable
    ): List<MinutelyVolume>

    fun findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
        companyCode: CompanyCode,
        afterEqualDateTime: LocalDateTime,
        beforeDateTime: LocalDateTime
    ): List<MinutelyVolume>

    fun findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeAsc(
        companyCode: CompanyCode,
        afterEqualDateTime: LocalDateTime,
        beforeDateTime: LocalDateTime
    ): List<MinutelyVolume>
}
