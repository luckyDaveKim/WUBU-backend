package com.wubu.api.domain.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyReq
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyRes

interface FavoriteCompanyService {

    fun retrieveFavoriteCompanies(pagingReqDto: PagingReqDto): List<FavoriteCompanyRes>

    fun registerFavoriteCompanies(favoriteCompanyReq: FavoriteCompanyReq)
}
