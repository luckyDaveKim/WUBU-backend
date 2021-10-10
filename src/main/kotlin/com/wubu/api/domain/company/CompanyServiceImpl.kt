package com.wubu.api.domain.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.interfaces.company.CompanyRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompanyServiceImpl(
    private val companyReader: CompanyReader
) : CompanyService {

    @Transactional
    override fun retrieveCompanies(pagingReqDto: PagingReqDto): List<CompanyRes> {
        return companyReader.getCompanies(pagingReqDto)
            .map { company -> CompanyRes.of(company) }
    }
}
