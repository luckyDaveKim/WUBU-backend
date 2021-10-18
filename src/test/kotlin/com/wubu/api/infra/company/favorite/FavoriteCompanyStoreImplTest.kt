package com.wubu.api.infra.company.favorite

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.favorite.FavoriteCompany
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class FavoriteCompanyStoreImplTest {

    @Mock
    private lateinit var favoriteCompanyRepository: FavoriteCompanyRepository

    @InjectMocks
    private lateinit var favoriteCompanyStoreImpl: FavoriteCompanyStoreImpl

    @Captor
    private lateinit var favoriteCompanyCaptor: ArgumentCaptor<FavoriteCompany>

    @Test
    fun `즐겨찾기 회사 리스트 저장 테스트`() {
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
        val companies = listOf(company1, company2)

        // when
        favoriteCompanyStoreImpl.storeFavoriteCompanies(companies)

        // then
        verify(favoriteCompanyRepository, times(2))
            .save(favoriteCompanyCaptor.capture())
        val favoriteCompany1 = FavoriteCompany(
            company = company1
        )
        val favoriteCompany2 = FavoriteCompany(
            company = company2
        )
        assertThat(favoriteCompanyCaptor.allValues).isEqualTo(listOf(favoriteCompany1, favoriteCompany2))
    }

    @Test
    fun `즐겨찾기 회사 삭제 테스트`() {
        // given

        // when
        favoriteCompanyStoreImpl.removeAllFavoriteCompanies()

        // then
        verify(favoriteCompanyRepository)
            .deleteAll()
    }
}
