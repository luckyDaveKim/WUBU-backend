package com.wubu.api.infra.company

import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, CompanyId> {

    fun findAllByIdIn(
        companyIds: List<CompanyId>
    ): List<Company>

    fun findAllByOrderByNameAsc(
        pageable: Pageable
    ): List<Company>
}
