package com.wubu.api.company.favorite.dto.res

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.company.favorite.dto.FavoriteCompanyDto
import com.wubu.api.company.favorite.entity.FavoriteCompany

data class FavoriteCompaniesResDto(
    @JsonValue
    val favoriteCompanies: Set<FavoriteCompanyDto>
) {
    companion object {
        fun of(favoriteCompanies: List<FavoriteCompany>): FavoriteCompaniesResDto {
            return FavoriteCompaniesResDto(
                favoriteCompanies.map(FavoriteCompanyDto::of)
                    .toSet()
            )
        }
    }
}
