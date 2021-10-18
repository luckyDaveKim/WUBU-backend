package com.wubu.api.application.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.favorite.FavoriteCompanyService
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyReq
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class FavoriteCompanyFacadeTest {

    @Mock
    private lateinit var favoriteCompanyService: FavoriteCompanyService

    @InjectMocks
    private lateinit var favoriteCompanyFacade: FavoriteCompanyFacade

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var company1: Company
    private lateinit var company2: Company

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        company1 = Company(
            id = CompanyId(companyCode1),
            name = "company name1",
            date = LocalDate.of(1991, 3, 26)
        )
        companyCode2 = CompanyCode("000002")
        company2 = Company(
            id = CompanyId(companyCode2),
            name = "company name2",
            date = LocalDate.of(1991, 3, 26)
        )
    }

    @Test
    fun `즐겨찾기 회사 리스트 조회 테스트`() {
        // given
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

        val companies = listOf(company1, company2)
        val favoriteCompanyResList = companies.map { company -> FavoriteCompanyRes.of(company) }

        given(favoriteCompanyService.retrieveFavoriteCompanies(pagingReqDto))
            .willReturn(favoriteCompanyResList)

        // when
        val foundCompanies = favoriteCompanyFacade.retrieveFavoriteCompanies(pagingReqDto)

        // then
        assertThat(foundCompanies).isEqualTo(favoriteCompanyResList)
    }

    @Test
    fun `즐겨찾기 회사 저장 테스트`() {
        // given
        val companyCodes = listOf(companyCode1, companyCode2)
        val favoriteCompanyReq = FavoriteCompanyReq(companyCodes)

        // when
        favoriteCompanyFacade.registerFavoriteCompanies(favoriteCompanyReq)

        // then
        verify(favoriteCompanyService)
            .registerFavoriteCompanies(favoriteCompanyReq)
    }
}
