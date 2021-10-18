package com.wubu.api.domain.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto

interface FavoriteCompanyReader {

    fun getFavoriteCompanies(pagingReqDto: PagingReqDto): List<FavoriteCompany>
}
