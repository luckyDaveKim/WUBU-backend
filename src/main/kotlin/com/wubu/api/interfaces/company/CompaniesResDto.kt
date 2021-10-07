package com.wubu.api.interfaces.company

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.domain.company.Company

data class CompaniesResDto(
    @JsonValue
    val companies: List<CompanyDto>
) {
    companion object {
        fun of(companies: List<Company>): CompaniesResDto {
            return CompaniesResDto(
                companies.map(CompanyDto::of)
                    .toList()
            )
        }
    }
}