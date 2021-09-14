package com.wubu.api.company.dto

import com.wubu.api.company.entity.Company

data class CompanyDto(
        val value: String,
        val label: String
) {
    companion object {
        fun of(company: Company): CompanyDto {
            return CompanyDto(
                    value = company.id.code.value,
                    label = "${company.name} (${company.id.code.value})"
            )
        }
    }
}