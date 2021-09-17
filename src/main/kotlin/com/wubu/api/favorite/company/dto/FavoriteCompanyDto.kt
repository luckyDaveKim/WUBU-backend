package com.wubu.api.favorite.company.dto

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.favorite.company.entity.FavoriteCompany

data class FavoriteCompanyDto(
    @JsonValue
    val companyCode: CompanyCode
) {
    companion object {
        fun of(favoriteCompany: FavoriteCompany): FavoriteCompanyDto {
            return FavoriteCompanyDto(favoriteCompany.company.id.companyCode)
        }
    }
}
