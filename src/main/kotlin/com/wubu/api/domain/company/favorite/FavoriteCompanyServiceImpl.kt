package com.wubu.api.domain.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.domain.company.CompanyReader
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyReq
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FavoriteCompanyServiceImpl(
    private val favoriteCompanyReader: FavoriteCompanyReader,
    private val favoriteCompanyStore: FavoriteCompanyStore,
    private val companyReader: CompanyReader
) : FavoriteCompanyService {

    @Transactional
    override fun retrieveFavoriteCompanies(pagingReqDto: PagingReqDto): List<FavoriteCompanyRes> {
        return favoriteCompanyReader.getFavoriteCompanies(pagingReqDto)
            .map { favoriteCompany -> FavoriteCompanyRes.of(favoriteCompany) }
    }

    @Transactional
    override fun registerFavoriteCompanies(favoriteCompanyReq: FavoriteCompanyReq) {
        favoriteCompanyStore.removeAllFavoriteCompanies()
        val companies = companyReader.getCompaniesByCodes(favoriteCompanyReq.companyCodes)
        favoriteCompanyStore.storeFavoriteCompanies(companies)
    }
}
