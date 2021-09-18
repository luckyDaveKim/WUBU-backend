package com.wubu.api.company.favorite.dto

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import com.wubu.api.company.favorite.entity.FavoriteCompany
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class FavoriteCompanyDtoTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val favoriteCompanyDto = FavoriteCompanyDto(companyCode1)

        // then
        assertThat(favoriteCompanyDto.companyCode).isEqualTo(companyCode1)
    }

    @Test
    fun `of 생성 테스트`() {
        // given
        val company = Company(
            id = CompanyId(companyCode1),
            name = "company1",
            date = LocalDate.of(1991, 3, 26)
        )
        val favoriteCompany = FavoriteCompany(1, company)

        // when
        val favoriteCompanyDto = FavoriteCompanyDto.of(favoriteCompany)

        // then
        assertThat(favoriteCompanyDto.companyCode).isEqualTo(companyCode1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val favoriteCompanyDto1 = FavoriteCompanyDto(companyCode1)
        val favoriteCompanyDto2 = FavoriteCompanyDto(companyCode1)

        // then
        assertThat(favoriteCompanyDto1).isEqualTo(favoriteCompanyDto2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val favoriteCompanyDto1 = FavoriteCompanyDto(companyCode1)
        val favoriteCompanyDto2 = FavoriteCompanyDto(companyCode2)

        // then
        assertThat(favoriteCompanyDto1).isNotEqualTo(favoriteCompanyDto2)
    }
}
