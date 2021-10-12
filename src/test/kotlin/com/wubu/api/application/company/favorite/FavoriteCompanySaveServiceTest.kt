package com.wubu.api.application.company.favorite

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.infra.company.CompanyRepository
import com.wubu.api.infra.company.favorite.FavoriteCompanyRepository
import com.wubu.api.interfaces.company.favorite.FavoriteCompanyReq
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.util.Optional

@ExtendWith(MockitoExtension::class)
internal class FavoriteCompanySaveServiceTest {

    @Mock
    private lateinit var favoriteCompanyRepository: FavoriteCompanyRepository

    @Mock
    private lateinit var companyRepository: CompanyRepository

    @InjectMocks
    private lateinit var favoriteCompanySaveService: FavoriteCompanySaveService

    @Captor
    private lateinit var captor: ArgumentCaptor<FavoriteCompany>

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
    fun `즐겨찾기 회사 저장 테스트`() {
        // given
        val companyCodes = listOf(companyCode1, companyCode2)
        val favoriteCompanyReq = FavoriteCompanyReq(companyCodes = companyCodes)

        given(companyRepository.findById(CompanyId(companyCode = companyCode1)))
            .willReturn(Optional.of(company1))
        given(companyRepository.findById(CompanyId(companyCode = companyCode2)))
            .willReturn(Optional.of(company2))

        // when
        favoriteCompanySaveService.saveCompanies(favoriteCompanyReq)

        // then
        verify(favoriteCompanyRepository)
            .deleteAll()
        verify(favoriteCompanyRepository, times(2))
            .save(captor.capture())
        val savedFavoriteCompanies = captor.allValues
        assertThat(savedFavoriteCompanies[0]).isNotNull
        assertThat(savedFavoriteCompanies[0].company).isEqualTo(company1)
        assertThat(savedFavoriteCompanies[1]).isNotNull
        assertThat(savedFavoriteCompanies[1].company).isEqualTo(company2)
    }

    @Test
    fun `즐겨찾기 회사 저장 실패 테스트`() {
        // given
        val companyCode = CompanyCode("999999")
        val companyCodes = listOf(companyCode)
        val favoriteCompanyReq = FavoriteCompanyReq(companyCodes = companyCodes)

        given(companyRepository.findById(CompanyId(companyCode = companyCode)))
            .willThrow(NotFoundCompanyException(companyCode))

        // when

        // then
        assertThatThrownBy { favoriteCompanySaveService.saveCompanies(favoriteCompanyReq) }
            .isInstanceOf(NotFoundCompanyException::class.java)
    }
}
