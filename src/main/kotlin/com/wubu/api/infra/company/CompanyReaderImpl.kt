package com.wubu.api.infra.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyReader
import org.springframework.stereotype.Component

@Component
class CompanyReaderImpl(
    private val companyRepository: CompanyRepository
) : CompanyReader {

    override fun getCompanies(pagingReqDto: PagingReqDto): List<Company> {
        return companyRepository.findAllByOrderByNameAsc(pagingReqDto.getPageable())
    }
}
