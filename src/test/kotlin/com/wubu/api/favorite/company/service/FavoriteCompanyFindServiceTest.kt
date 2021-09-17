package com.wubu.api.favorite.company.service

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import com.wubu.api.favorite.company.dto.res.FavoriteCompaniesResDto
import com.wubu.api.favorite.company.entity.FavoriteCompany
import com.wubu.api.favorite.company.repository.FavoriteCompanyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class FavoriteCompanyFindServiceTest {

    @Mock
    private lateinit var favoriteCompanyRepository: FavoriteCompanyRepository

    @InjectMocks
    private lateinit var favoriteCompanyFindService: FavoriteCompanyFindService

    @Test
    fun `즐겨찾기 회사 조회 테스트`() {
        // given
        val company1 = Company(
            id = CompanyId(CompanyCode("000001")),
            name = "company name1",
            date = LocalDate.of(1991, 3, 26)
        )
        val company2 = Company(
            id = CompanyId(CompanyCode("000002")),
            name = "company name2",
            date = LocalDate.of(1991, 3, 26)
        )
        val favoriteCompany1 = FavoriteCompany(company = company1)
        val favoriteCompany2 = FavoriteCompany(company = company2)
        val favoriteCompanies = listOf(favoriteCompany1, favoriteCompany2)
        val favoriteCompaniesResDto = FavoriteCompaniesResDto.of(favoriteCompanies)

        given(favoriteCompanyRepository.findAll())
            .willReturn(favoriteCompanies)

        // when
        val foundFavoriteCompanies = favoriteCompanyFindService.findCompanies()

        // then
        assertThat(foundFavoriteCompanies).isNotNull
        assertThat(foundFavoriteCompanies).isEqualTo(favoriteCompaniesResDto)
    }
}
