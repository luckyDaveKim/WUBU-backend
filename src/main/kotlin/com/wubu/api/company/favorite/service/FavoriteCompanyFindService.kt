package com.wubu.api.company.favorite.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.company.favorite.dto.res.FavoriteCompaniesResDto
import com.wubu.api.company.favorite.repository.FavoriteCompanyRepository
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
