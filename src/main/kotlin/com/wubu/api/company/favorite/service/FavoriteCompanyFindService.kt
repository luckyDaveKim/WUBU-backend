package com.wubu.api.company.favorite.service

import com.wubu.api.company.favorite.dto.res.FavoriteCompaniesResDto
import com.wubu.api.company.favorite.repository.FavoriteCompanyRepository
import org.springframework.stereotype.Service

@Service
class FavoriteCompanyFindService(
    private val favoriteCompanyRepository: FavoriteCompanyRepository
) {
    fun findCompanies(): FavoriteCompaniesResDto {
        val favoriteCompanies = favoriteCompanyRepository.findAll()
        return FavoriteCompaniesResDto.of(favoriteCompanies)
    }
}
