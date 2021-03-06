package com.wubu.api.infra.price.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.price.daily.DailyPrice
import com.wubu.api.domain.price.daily.DailyPriceId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface DailyPriceRepository : JpaRepository<DailyPrice, DailyPriceId> {

    fun findAllByIdCompanyCodeOrderByIdDateDesc(
        companyCode: CompanyCode,
        pageable: Pageable
    ): List<DailyPrice>

    fun findAllById_CompanyCodeAndId_DateGreaterThanEqualAndId_DateLessThanOrderByIdDateAsc(
        companyCode: CompanyCode,
        greaterThanEqualDate: LocalDate,
        lessThanDate: LocalDate
    ): List<DailyPrice>
}
