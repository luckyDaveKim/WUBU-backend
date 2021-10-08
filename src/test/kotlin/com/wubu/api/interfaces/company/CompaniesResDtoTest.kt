package com.wubu.api.interfaces.company

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class CompaniesResDtoTest {

    private lateinit var company1: Company
    private lateinit var company2: Company
    private lateinit var company3: Company
    private lateinit var company4: Company

    @BeforeEach
    fun setUp() {
        company1 = Company(
            id = CompanyId(CompanyCode("000001")),
            name = "company name1",
            date = LocalDate.of(1991, 3, 26)
        )
        company2 = Company(
            id = CompanyId(CompanyCode("000002")),
            name = "company name2",
            date = LocalDate.of(1991, 3, 27)
        )
        company3 = Company(
            id = CompanyId(CompanyCode("000003")),
            name = "company name3",
            date = LocalDate.of(1991, 3, 28)
        )
        company4 = Company(
            id = CompanyId(CompanyCode("000004")),
            name = "company name4",
            date = LocalDate.of(1991, 3, 29)
        )
    }

    @Test
    fun `생성 테스트`() {
        // given
        val companyRes1 = CompanyRes.of(company1)
        val companyRes2 = CompanyRes.of(company2)
        val companiesDto = listOf(companyRes1, companyRes2)

        // when
        val companiesResDto = CompaniesResDto(companiesDto)

        // then
        assertThat(companiesResDto).isNotNull
        assertThat(companiesResDto.companies).isNotNull
        assertThat(companiesResDto.companies.size).isEqualTo(2)
        assertThat(companiesResDto.companies[0]).isEqualTo(companyRes1)
        assertThat(companiesResDto.companies[1]).isEqualTo(companyRes2)
    }

    @Test
    fun `of 생성 테스트`() {
        // given
        val companyRes1 = CompanyRes.of(company1)
        val companyRes2 = CompanyRes.of(company2)
        val companies = listOf(company1, company2)

        // when
        val companiesResDto = CompaniesResDto.of(companies)

        // then
        assertThat(companiesResDto).isNotNull
        assertThat(companiesResDto.companies).isNotNull
        assertThat(companiesResDto.companies.size).isEqualTo(2)
        assertThat(companiesResDto.companies[0]).isEqualTo(companyRes1)
        assertThat(companiesResDto.companies[1]).isEqualTo(companyRes2)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val companies1 = listOf(company1, company2)
        val companies2 = listOf(company1, company2)

        // when
        val companiesResDto1 = CompaniesResDto.of(companies1)
        val companiesResDto2 = CompaniesResDto.of(companies2)

        // then
        assertThat(companiesResDto1).isEqualTo(companiesResDto2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val companies1 = listOf(company1, company2)
        val companies2 = listOf(company3, company4)

        // when
        val companiesResDto1 = CompaniesResDto.of(companies1)
        val companiesResDto2 = CompaniesResDto.of(companies2)

        // then
        assertThat(companiesResDto1).isNotEqualTo(companiesResDto2)
    }
}
