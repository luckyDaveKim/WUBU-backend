package com.wubu.api.infra.company.favorite

import com.wubu.api.domain.company.favorite.FavoriteCompany
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FavoriteCompanyRepository : JpaRepository<FavoriteCompany, Long> {

    fun findAllByOrderByCompany_NameAsc(
        pageable: Pageable
    ): List<FavoriteCompany>
}
