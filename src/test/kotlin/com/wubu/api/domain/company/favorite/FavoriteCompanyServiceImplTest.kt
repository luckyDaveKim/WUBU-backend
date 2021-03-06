package com.wubu.api.domain.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.CompanyReader
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyReq
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class FavoriteCompanyServiceImplTest {

    @Mock
    private lateinit var favoriteCompanyReader: FavoriteCompanyReader

    @Mock
    private lateinit var favoriteCompanyStore: FavoriteCompanyStore

    @Mock
    private lateinit var companyReader: CompanyReader

    @InjectMocks
    private lateinit var favoriteCompanyService: FavoriteCompanyServiceImpl

    @Captor
    private lateinit var companyCodesCaptor: ArgumentCaptor<List<CompanyCode>>
    private lateinit var companiesCaptor: ArgumentCaptor<List<Company>>

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
    fun `???????????? ?????? ????????? ?????? ?????????`() {
        // given
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

        val companies = listOf(company1, company2)
        val favoriteCompanies = companies.map { company -> FavoriteCompany(company = company) }
        val favoriteCompanyResList = companies.map { company -> FavoriteCompanyRes.of(company) }

        given(favoriteCompanyReader.getFavoriteCompanies(pagingReqDto))
            .willReturn(favoriteCompanies)

        // when
        val foundCompanies = favoriteCompanyService.retrieveFavoriteCompanies(pagingReqDto)

        // then
        assertThat(foundCompanies).isEqualTo(favoriteCompanyResList)
    }

    @Test
    fun `???????????? ?????? ?????? ?????????`() {
        // given
        val companyCodes = listOf(companyCode1, companyCode2)
        val favoriteCompanyReq = FavoriteCompanyReq(companyCodes)
        val companies = listOf(company1, company2)

        given(companyReader.getCompaniesByCodes(favoriteCompanyReq.companyCodes))
            .willReturn(companies)

        // when
        favoriteCompanyService.registerFavoriteCompanies(favoriteCompanyReq)

        // then
        verify(favoriteCompanyStore)
            .removeAllFavoriteCompanies()
        verify(companyReader)
            .getCompaniesByCodes(favoriteCompanyReq.companyCodes)
        verify(favoriteCompanyStore)
            .storeFavoriteCompanies(companies)
    }
}
