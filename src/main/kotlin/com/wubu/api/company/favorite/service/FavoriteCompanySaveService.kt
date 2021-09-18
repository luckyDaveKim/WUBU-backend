package com.wubu.api.company.favorite.service

import com.wubu.api.company.entity.CompanyId
import com.wubu.api.company.favorite.dto.req.FavoriteCompanyReqDto
import com.wubu.api.company.favorite.entity.FavoriteCompany
import com.wubu.api.company.favorite.exception.NotFoundCompanyException
import com.wubu.api.company.favorite.repository.FavoriteCompanyRepository
import com.wubu.api.company.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class FavoriteCompanySaveService(
    private val favoriteCompanyRepository: FavoriteCompanyRepository,
    private val companyRepository: CompanyRepository
) {

    fun saveCompanies(favoriteCompanyReqDto: FavoriteCompanyReqDto) {
        favoriteCompanyRepository.deleteAll()

        val companies = favoriteCompanyReqDto.companyCodes
            .map { companyCode -> CompanyId(companyCode) }
            .map { companyId ->
                companyRepository.findById(companyId)
                    .orElseThrow { NotFoundCompanyException(companyId.companyCode) }
            }
            .toList()

        companies
            .map { company -> FavoriteCompany(company = company) }
            .map { favoriteCompany -> favoriteCompanyRepository.save(favoriteCompany) }
    }
}
