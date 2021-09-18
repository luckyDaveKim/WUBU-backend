package com.wubu.api.stockvalue.minutely.price.repository

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.stockvalue.minutely.price.entity.MinutelyPrice
import com.wubu.api.stockvalue.minutely.price.entity.MinutelyPriceId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MinutelyPriceRepository : JpaRepository<MinutelyPrice, MinutelyPriceId> {

    fun findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
        companyCode: CompanyCode,
        afterEqualDateTime: LocalDateTime,
        beforeDateTime: LocalDateTime
    ): List<MinutelyPrice>
}
