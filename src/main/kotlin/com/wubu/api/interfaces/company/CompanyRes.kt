package com.wubu.api.interfaces.company

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.favorite.FavoriteCompany

data class CompanyRes(
    val code: CompanyCode,
    val name: String
) {
    companion object {
        fun of(company: Company): CompanyRes {
            return CompanyRes(
                code = company.id.companyCode,
                name = company.name
            )
        }

        fun of(favoriteCompany: FavoriteCompany): CompanyRes {
            return of(favoriteCompany.company)
        }
    }
}
