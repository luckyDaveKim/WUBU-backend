package com.wubu.api.application.company.favorite

import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.infra.company.CompanyRepository
import com.wubu.api.infra.company.favorite.FavoriteCompanyRepository
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyReq
import org.springframework.stereotype.Service

@Service
class FavoriteCompanySaveService(
    private val favoriteCompanyRepository: FavoriteCompanyRepository,
    private val companyRepository: CompanyRepository
) {

    fun saveCompanies(favoriteCompanyReq: FavoriteCompanyReq) {
        favoriteCompanyRepository.deleteAll()

        val companies = favoriteCompanyReq.companyCodes
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
