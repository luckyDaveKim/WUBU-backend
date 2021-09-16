package com.wubu.api.company.dto.res

import com.fasterxml.jackson.annotation.JsonValue
import com.wubu.api.company.dto.CompanyDto
import com.wubu.api.company.entity.Company

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
