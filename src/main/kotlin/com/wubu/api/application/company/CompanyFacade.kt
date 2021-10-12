package com.wubu.api.application.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.domain.company.CompanyService
import com.wubu.api.interfaces.company.CompanyRes
import org.springframework.stereotype.Service

@Service
class CompanyFacade(
    private val companyService: CompanyService
) {

    fun retrieveCompanies(pagingReqDto: PagingReqDto): List<CompanyRes> {
        return companyService.retrieveCompanies(pagingReqDto)
    }
}
