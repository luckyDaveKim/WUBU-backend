package com.wubu.api.domain.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import java.util.*

interface CompanyReader {

    fun getCompaniesByCodes(companyCodes: List<CompanyCode>): List<Company>

    fun getCompanies(pagingReqDto: PagingReqDto): List<Company>

    fun getCompanyByCode(companyCode: CompanyCode): Company
}
