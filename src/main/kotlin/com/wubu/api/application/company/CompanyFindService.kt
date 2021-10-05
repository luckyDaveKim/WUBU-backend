package com.wubu.api.application.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.infra.company.CompanyRepository
import com.wubu.api.interfaces.company.CompaniesResDto
import org.springframework.stereotype.Service

@Service
class CompanyFindService(
    private val companyRepository: CompanyRepository
) {

    fun findCompanies(pagingReqDto: PagingReqDto): CompaniesResDto {
        val companies = companyRepository.findAllByOrderByNameAsc(
            pageable = pagingReqDto.getPageable()
        )
        return CompaniesResDto.of(companies)
    }
}
