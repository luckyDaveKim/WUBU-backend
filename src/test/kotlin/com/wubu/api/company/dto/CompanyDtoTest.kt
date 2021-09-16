package com.wubu.api.company.dto

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CompanyDtoTest {

    lateinit var company1: Company
    lateinit var company2: Company

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
    }

    @Test
    fun `생성 테스트`() {
        // given
        val value = "value"
        val label = "label"

        // when
        val companyDto = CompanyDto(
            value = value,
            label = label
        )

        // then
        assertThat(companyDto.value).isEqualTo(value)
        assertThat(companyDto.label).isEqualTo(label)
    }

    @Test
    fun `of 생성 테스트`() {
        // given

        // when
        val companyDto = CompanyDto.of(company1)

        // then
        assertThat(companyDto.value).isEqualTo(company1.id.companyCode.value)
        assertThat(companyDto.label).isEqualTo("${company1.name} (${company1.id.companyCode.value})")
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val companyDto1 = CompanyDto.of(company1)
        val companyDto2 = CompanyDto.of(company1)

        // then
        assertThat(companyDto1).isEqualTo(companyDto2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val companyDto1 = CompanyDto.of(company1)
        val companyDto2 = CompanyDto.of(company2)

        // then
        assertThat(companyDto1).isNotEqualTo(companyDto2)
    }
}
