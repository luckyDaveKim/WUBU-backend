package com.wubu.api.application.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.infra.company.favorite.FavoriteCompanyRepository
import com.wubu.api.interfaces.company.CompanyRes
import org.springframework.stereotype.Service

@Service
class FavoriteCompanyFindService(
    private val favoriteCompanyRepository: FavoriteCompanyRepository
) {
    fun findCompanies(pagingReqDto: PagingReqDto): List<CompanyRes> {
        val favoriteCompanies = favoriteCompanyRepository.findAllByOrderByCompany_NameAsc(
            pageable = pagingReqDto.getPageable()
        )
        return favoriteCompanies.map { favoriteCompany -> CompanyRes.of(favoriteCompany) }
    }
}
