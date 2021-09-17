package com.wubu.api.favorite.company.dto.res

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.favorite.company.dto.FavoriteCompanyDto
import com.wubu.api.favorite.company.entity.FavoriteCompany

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
