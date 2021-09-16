package com.wubu.api.stockvalue.daily.volume.repository

import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.daily.price.entity.DailyPrice
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolume
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolumeId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DailyVolumeRepository : JpaRepository<DailyVolume, DailyVolumeId> {
    fun findAllByIdCodeOrderByIdDateDesc(
            code: Code,
            pageable: Pageable): List<DailyVolume>

    fun findAllByIdCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
            code: Code,
            date: LocalDate): List<DailyVolume>
}
