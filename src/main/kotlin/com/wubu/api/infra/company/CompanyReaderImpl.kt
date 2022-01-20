package com.wubu.api.infra.company

import com.wubu.api.application.company.favorite.NotFoundCompanyException
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.CompanyReader
import org.springframework.stereotype.Component

@Component
class CompanyReaderImpl(
    private val companyRepository: CompanyRepository
) : CompanyReader {

    override fun getCompaniesByCodes(companyCodes: List<CompanyCode>): List<Company> {
        val companyIds = companyCodes.map { companyCode -> CompanyId(companyCode) }
        return companyRepository.findAllByIdIn(companyIds)
    }

    override fun getCompanies(pagingReqDto: PagingReqDto): List<Company> {
        return companyRepository.findAllByOrderByNameAsc(pagingReqDto.getPageable())
    }

    override fun getCompanyByCode(companyCode: CompanyCode): Company {
        val companyId = CompanyId(companyCode)
        return companyRepository.findById(companyId)
            .orElseThrow { NotFoundCompanyException(companyCode) }
    }
}
