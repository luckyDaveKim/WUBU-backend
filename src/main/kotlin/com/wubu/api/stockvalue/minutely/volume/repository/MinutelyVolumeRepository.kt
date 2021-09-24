package com.wubu.api.stockvalue.minutely.volume.repository

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.stockvalue.minutely.volume.entity.MinutelyVolume
import com.wubu.api.stockvalue.minutely.volume.entity.MinutelyVolumeId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
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
}
