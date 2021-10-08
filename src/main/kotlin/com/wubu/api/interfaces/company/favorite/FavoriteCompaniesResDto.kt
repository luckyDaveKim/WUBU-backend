package com.wubu.api.interfaces.company.favorite

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.interfaces.company.CompanyRes

data class FavoriteCompaniesResDto(
    @JsonValue
    val companyResSet: Set<CompanyRes>
) {
    companion object {
        fun of(favoriteCompanies: List<FavoriteCompany>): FavoriteCompaniesResDto {
            return FavoriteCompaniesResDto(
                favoriteCompanies.map(CompanyRes::of)
                    .toSet()
            )
        }
    }
}
