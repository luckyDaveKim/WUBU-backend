package com.wubu.api.application.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.infra.company.favorite.FavoriteCompanyRepository
import com.wubu.api.interfaces.company.favorite.FavoriteCompaniesResDto
import org.springframework.stereotype.Service

@Service
class FavoriteCompanyFindService(
    private val favoriteCompanyRepository: FavoriteCompanyRepository
) {
    fun findCompanies(pagingReqDto: PagingReqDto): FavoriteCompaniesResDto {
        val favoriteCompanies = favoriteCompanyRepository.findAllByOrderByCompany_NameAsc(
            pageable = pagingReqDto.getPageable()
        )
        return FavoriteCompaniesResDto.of(favoriteCompanies)
    }
}
