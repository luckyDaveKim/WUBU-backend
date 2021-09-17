package com.wubu.api.favorite.company.repository

import com.wubu.api.favorite.company.entity.FavoriteCompany
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FavoriteCompanyRepository : JpaRepository<FavoriteCompany, Long>
