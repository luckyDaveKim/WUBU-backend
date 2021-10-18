package com.wubu.api.domain.company.favorite

import com.wubu.api.domain.company.Company

interface FavoriteCompanyStore {

    fun storeFavoriteCompanies(companies: List<Company>)

    fun removeAllFavoriteCompanies()
}
