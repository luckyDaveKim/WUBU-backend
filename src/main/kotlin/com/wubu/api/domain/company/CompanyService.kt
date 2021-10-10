package com.wubu.api.domain.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.interfaces.company.CompanyRes

interface CompanyService {

    fun retrieveCompanies(pagingReqDto: PagingReqDto): List<CompanyRes>
}
