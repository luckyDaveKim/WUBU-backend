package com.wubu.api.company.favorite.dto.res

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.company.dto.CompanyDto
import com.wubu.api.company.favorite.entity.FavoriteCompany

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
