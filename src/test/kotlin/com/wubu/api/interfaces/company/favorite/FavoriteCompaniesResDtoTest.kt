package com.wubu.api.interfaces.company.favorite

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.favorite.FavoriteCompany
import com.wubu.api.interfaces.company.CompanyRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class FavoriteCompaniesResDtoTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var companyName1: String
    private lateinit var companyName2: String
    private lateinit var companyRes1: CompanyRes
    private lateinit var companyRes2: CompanyRes

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        companyName1 = "company name1"
        companyName2 = "company name2"
        companyRes1 = CompanyRes(
            code = companyCode1,
            name = companyName1
        )
        companyRes2 = CompanyRes(
            code = companyCode2,
            name = companyName2
        )
    }

    @Test
    fun `생성 테스트`() {
        // given
        val favoriteCompanyDtoSet = setOf(companyRes1, companyRes2)

        // when
        val favoriteCompaniesResDto = FavoriteCompaniesResDto(favoriteCompanyDtoSet)

        // then
        assertThat(favoriteCompaniesResDto).isNotNull
        assertThat(favoriteCompaniesResDto.companyResSet).isEqualTo(favoriteCompanyDtoSet)
        assertThat(favoriteCompaniesResDto.companyResSet).contains(companyRes1, companyRes2)
        assertThat(favoriteCompaniesResDto.companyResSet.size).isEqualTo(2)
    }

    @Test
    fun `of 생성 테스트`() {
        // given
        val company1 = Company(
            id = CompanyId(companyCode1),
            name = companyName1,
            date = LocalDate.of(1991, 3, 26)
        )
        val favoriteCompany1 = FavoriteCompany(1, company1)
        val company2 = Company(
            id = CompanyId(companyCode2),
            name = companyName2,
            date = LocalDate.of(1991, 3, 26)
        )
        val favoriteCompany2 = FavoriteCompany(2, company2)
        val favoriteCompanies = listOf(favoriteCompany1, favoriteCompany2)

        val companyRes1 = CompanyRes.of(company1)
        val companyRes2 = CompanyRes.of(company2)
        val companyDtoSet = setOf(companyRes1, companyRes2)

        // when
        val favoriteCompaniesResDto = FavoriteCompaniesResDto.of(favoriteCompanies)

        // then
        assertThat(favoriteCompaniesResDto).isNotNull
        assertThat(favoriteCompaniesResDto.companyResSet).isEqualTo(companyDtoSet)
        assertThat(favoriteCompaniesResDto.companyResSet).contains(companyRes1, companyRes2)
        assertThat(favoriteCompaniesResDto.companyResSet.size).isEqualTo(2)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val favoriteCompanyDtoSet1 = setOf(companyRes1)

        // when
        val favoriteCompaniesResDto1 = FavoriteCompaniesResDto(favoriteCompanyDtoSet1)
        val favoriteCompaniesResDto2 = FavoriteCompaniesResDto(favoriteCompanyDtoSet1)

        // then
        assertThat(favoriteCompaniesResDto1).isEqualTo(favoriteCompaniesResDto2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val favoriteCompanyDtoSet1 = setOf(companyRes1)
        val favoriteCompanyDtoSet2 = setOf(companyRes2)

        // when
        val favoriteCompaniesResDto1 = FavoriteCompaniesResDto(favoriteCompanyDtoSet1)
        val favoriteCompaniesResDto2 = FavoriteCompaniesResDto(favoriteCompanyDtoSet2)

        // then
        assertThat(favoriteCompaniesResDto1).isNotEqualTo(favoriteCompaniesResDto2)
    }
}
