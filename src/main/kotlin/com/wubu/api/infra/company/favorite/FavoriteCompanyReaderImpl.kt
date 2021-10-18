package com.wubu.api.infra.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.domain.company.favorite.FavoriteCompanyReader
import org.springframework.stereotype.Component

@Component
class FavoriteCompanyReaderImpl(
    private val favoriteCompanyRepository: FavoriteCompanyRepository
) : FavoriteCompanyReader {

    override fun getFavoriteCompanies(pagingReqDto: PagingReqDto): List<FavoriteCompany> {
        // TODO : INFO 정보로 반환
        return favoriteCompanyRepository.findAllByOrderByCompany_NameAsc(pagingReqDto.getPageable())
    }
}
