package com.wubu.api.infra.price.minutely

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.price.minutely.MinutelyPrice
import com.wubu.api.domain.price.minutely.MinutelyPriceId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface MinutelyPriceRepository : JpaRepository<MinutelyPrice, MinutelyPriceId> {

    fun findAllById_CompanyCodeOrderById_DateTimeDesc(
        companyCode: CompanyCode,
        pageable: Pageable
    ): List<MinutelyPrice>

    fun findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeAsc(
        companyCode: CompanyCode,
        afterEqualDateTime: LocalDateTime,
        beforeDateTime: LocalDateTime
    ): List<MinutelyPrice>
}
