package com.wubu.api.company.dto

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.entity.Company

data class CompanyDto(
    val code: CompanyCode,
    val name: String
) {
    companion object {
        fun of(company: Company): CompanyDto {
            return CompanyDto(
                code = company.id.companyCode,
                name = company.name
            )
        }
    }
}
