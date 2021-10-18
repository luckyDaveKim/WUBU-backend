package com.wubu.api.interfaces.company.favorite

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.favorite.FavoriteCompany

data class FavoriteCompanyReq(
    val companyCodes: List<CompanyCode>
)

data class FavoriteCompanyRes(
    val code: CompanyCode,
    val name: String
) {
    companion object {
        fun of(company: Company): FavoriteCompanyRes {
            return FavoriteCompanyRes(
                code = company.id.companyCode,
                name = company.name
            )
        }

        fun of(favoriteCompany: FavoriteCompany): FavoriteCompanyRes {
            return of(favoriteCompany.company)
        }
    }
}
