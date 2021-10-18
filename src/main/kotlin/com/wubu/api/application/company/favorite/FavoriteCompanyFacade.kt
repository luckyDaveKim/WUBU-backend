package com.wubu.api.application.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.domain.company.favorite.FavoriteCompanyService
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyReq
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyRes
import org.springframework.stereotype.Service

@Service
class FavoriteCompanyFacade(
    private val favoriteCompanyService: FavoriteCompanyService
) {

    fun retrieveFavoriteCompanies(pagingReqDto: PagingReqDto): List<FavoriteCompanyRes> {
        return favoriteCompanyService.retrieveFavoriteCompanies(pagingReqDto)
    }

    fun registerFavoriteCompanies(favoriteCompanyReq: FavoriteCompanyReq) {
        favoriteCompanyService.registerFavoriteCompanies(favoriteCompanyReq)
    }
}
