package com.wubu.api.company.favorite.dto.res

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import com.wubu.api.company.favorite.dto.FavoriteCompanyDto
import com.wubu.api.company.favorite.entity.FavoriteCompany
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class FavoriteCompaniesResDtoTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var favoriteCompanyDto1: FavoriteCompanyDto
    private lateinit var favoriteCompanyDto2: FavoriteCompanyDto

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        favoriteCompanyDto1 = FavoriteCompanyDto(companyCode1)
        favoriteCompanyDto2 = FavoriteCompanyDto(companyCode2)
    }

    @Test
    fun `생성 테스트`() {
        // given
        val favoriteCompanyDtoSet = setOf(favoriteCompanyDto1, favoriteCompanyDto2)

        // when
        val favoriteCompaniesResDto = FavoriteCompaniesResDto(favoriteCompanyDtoSet)

        // then
        assertThat(favoriteCompaniesResDto).isNotNull
        assertThat(favoriteCompaniesResDto.favoriteCompanies).isEqualTo(favoriteCompanyDtoSet)
        assertThat(favoriteCompaniesResDto.favoriteCompanies).contains(favoriteCompanyDto1, favoriteCompanyDto2)
        assertThat(favoriteCompaniesResDto.favoriteCompanies.size).isEqualTo(2)
    }

    @Test
    fun `of 생성 테스트`() {
        // given
        val company1 = Company(
            id = CompanyId(companyCode1),
            name = "company1",
            date = LocalDate.of(1991, 3, 26)
        )
        val favoriteCompany1 = FavoriteCompany(1, company1)
        val favoriteCompanyDto1 = FavoriteCompanyDto.of(favoriteCompany1)

        val company2 = Company(
            id = CompanyId(companyCode2),
            name = "company2",
            date = LocalDate.of(1991, 3, 26)
        )
        val favoriteCompany2 = FavoriteCompany(2, company2)
        val favoriteCompanyDto2 = FavoriteCompanyDto.of(favoriteCompany2)

        val favoriteCompanies = listOf(favoriteCompany1, favoriteCompany2)
        val favoriteCompanyDtoSet = setOf(favoriteCompanyDto1, favoriteCompanyDto2)

        // when
        val favoriteCompaniesResDto = FavoriteCompaniesResDto.of(favoriteCompanies)

        // then
        assertThat(favoriteCompaniesResDto).isNotNull
        assertThat(favoriteCompaniesResDto.favoriteCompanies).isEqualTo(favoriteCompanyDtoSet)
        assertThat(favoriteCompaniesResDto.favoriteCompanies).contains(favoriteCompanyDto1, favoriteCompanyDto2)
        assertThat(favoriteCompaniesResDto.favoriteCompanies.size).isEqualTo(2)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val favoriteCompanyDtoSet1 = setOf(favoriteCompanyDto1)

        // when
        val favoriteCompaniesResDto1 = FavoriteCompaniesResDto(favoriteCompanyDtoSet1)
        val favoriteCompaniesResDto2 = FavoriteCompaniesResDto(favoriteCompanyDtoSet1)

        // then
        assertThat(favoriteCompaniesResDto1).isEqualTo(favoriteCompaniesResDto2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val favoriteCompanyDtoSet1 = setOf(favoriteCompanyDto1)
        val favoriteCompanyDtoSet2 = setOf(favoriteCompanyDto2)

        // when
        val favoriteCompaniesResDto1 = FavoriteCompaniesResDto(favoriteCompanyDtoSet1)
        val favoriteCompaniesResDto2 = FavoriteCompaniesResDto(favoriteCompanyDtoSet2)

        // then
        assertThat(favoriteCompaniesResDto1).isNotEqualTo(favoriteCompaniesResDto2)
    }
}
