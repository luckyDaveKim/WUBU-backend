package com.wubu.api.favorite.company.service

import com.wubu.api.favorite.company.dto.res.FavoriteCompaniesResDto
import com.wubu.api.favorite.company.repository.FavoriteCompanyRepository
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
