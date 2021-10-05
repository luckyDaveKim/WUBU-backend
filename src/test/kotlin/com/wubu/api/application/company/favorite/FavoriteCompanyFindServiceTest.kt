package com.wubu.api.application.company.favorite

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.infra.company.favorite.FavoriteCompanyRepository
import com.wubu.api.interfaces.company.favorite.FavoriteCompaniesResDto
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
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

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

        given(
            favoriteCompanyRepository.findAllByOrderByCompany_NameAsc(
                pagingReqDto.getPageable()
            )
        )
            .willReturn(favoriteCompanies)

        // when
        val foundFavoriteCompanies = favoriteCompanyFindService.findCompanies(
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundFavoriteCompanies).isNotNull
        assertThat(foundFavoriteCompanies).isEqualTo(favoriteCompaniesResDto)
    }
}
