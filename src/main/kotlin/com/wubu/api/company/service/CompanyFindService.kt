package com.wubu.api.company.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.company.dto.res.CompaniesResDto
import com.wubu.api.company.repository.CompanyRepository
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
