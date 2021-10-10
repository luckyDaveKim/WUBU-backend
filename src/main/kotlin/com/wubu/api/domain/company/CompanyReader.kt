package com.wubu.api.domain.company

import com.wubu.api.common.web.dto.PagingReqDto

interface CompanyReader {

    fun getCompanies(pagingReqDto: PagingReqDto): List<Company>
}
