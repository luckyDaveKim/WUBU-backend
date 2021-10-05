package com.wubu.api.interfaces.company.favorite

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.interfaces.company.CompanyDto

data class FavoriteCompaniesResDto(
    @JsonValue
    val companyDtoSet: Set<CompanyDto>
) {
    companion object {
        fun of(favoriteCompanies: List<FavoriteCompany>): FavoriteCompaniesResDto {
            return FavoriteCompaniesResDto(
                favoriteCompanies.map(CompanyDto::of)
                    .toSet()
            )
        }
    }
}
