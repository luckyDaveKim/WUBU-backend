package com.wubu.api.infra.company.favorite

import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.domain.company.favorite.FavoriteCompanyStore
import org.springframework.stereotype.Component

@Component
class FavoriteCompanyStoreImpl(
    private val favoriteCompanyRepository: FavoriteCompanyRepository
) : FavoriteCompanyStore {

    override fun storeFavoriteCompanies(companies: List<Company>) {
        companies
            .map { company -> FavoriteCompany(company = company) }
            .map { favoriteCompany -> favoriteCompanyRepository.save(favoriteCompany) }
    }

    override fun removeAllFavoriteCompanies() {
        favoriteCompanyRepository.deleteAll()
    }
}
