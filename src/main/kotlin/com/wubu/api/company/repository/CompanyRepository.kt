package com.wubu.api.company.repository

import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, CompanyId> {

    fun findAllByOrderByNameAsc(
        pageable: Pageable
    ): List<Company>
}
