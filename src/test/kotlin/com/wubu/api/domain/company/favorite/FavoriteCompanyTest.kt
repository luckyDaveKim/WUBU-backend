package com.wubu.api.domain.company.favorite

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class FavoriteCompanyTest {

    private lateinit var userId: String
    private lateinit var company1: Company
    private lateinit var company2: Company

    @BeforeEach
    fun setUp() {
        userId = "dave"

        company1 = Company(
            id = CompanyId(CompanyCode("000001")),
            name = "company name1",
            date = LocalDate.of(1991, 3, 26)
        )
        company2 = Company(
            id = CompanyId(CompanyCode("000002")),
            name = "company name2",
            date = LocalDate.of(1991, 3, 26)
        )
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val favoriteCompany = FavoriteCompany(
            company = company1
        )

        // then
        assertThat(favoriteCompany).isNotNull
        assertThat(favoriteCompany.id).isNotNull
        assertThat(favoriteCompany.company).isEqualTo(company1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val favoriteCompany1 = FavoriteCompany(
            id = 1,
            company = company1
        )
        val favoriteCompany2 = FavoriteCompany(
            id = 1,
            company = company1
        )

        // then
        assertThat(favoriteCompany1).isEqualTo(favoriteCompany2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val favoriteCompany1 = FavoriteCompany(
            id = 1,
            company = company1
        )
        val favoriteCompany2 = FavoriteCompany(
            id = 2,
            company = company2
        )

        // then
        assertThat(favoriteCompany1).isNotEqualTo(favoriteCompany2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val favoriteCompany1 = FavoriteCompany(
            id = 1,
            company = company1
        )
        val favoriteCompany2 = FavoriteCompany(
            id = 1,
            company = company1
        )

        // then
        assertThat(favoriteCompany1.hashCode()).isEqualTo(favoriteCompany2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val favoriteCompany1 = FavoriteCompany(
            id = 1,
            company = company1
        )
        val favoriteCompany2 = FavoriteCompany(
            id = 2,
            company = company2
        )

        // then
        assertThat(favoriteCompany1.hashCode()).isNotEqualTo(favoriteCompany2.hashCode())
    }
}
