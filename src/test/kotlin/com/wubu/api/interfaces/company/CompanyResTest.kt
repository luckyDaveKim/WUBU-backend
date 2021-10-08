package com.wubu.api.interfaces.company

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class CompanyResTest {

    private lateinit var company1: Company
    private lateinit var company2: Company

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
        val companyCode = CompanyCode("000001")
        val companyName = "company name"

        // when
        val companyRes = CompanyRes(
            code = companyCode,
            name = companyName
        )

        // then
        assertThat(companyRes.code).isEqualTo(companyCode)
        assertThat(companyRes.name).isEqualTo(companyName)
    }

    @Test
    fun `of 생성 테스트`() {
        // given

        // when
        val companyRes = CompanyRes.of(company1)

        // then
        assertThat(companyRes.code).isEqualTo(company1.id.companyCode)
        assertThat(companyRes.name).isEqualTo(company1.name)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val companyRes1 = CompanyRes.of(company1)
        val companyRes2 = CompanyRes.of(company1)

        // then
        assertThat(companyRes1).isEqualTo(companyRes2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val companyRes1 = CompanyRes.of(company1)
        val companyRes2 = CompanyRes.of(company2)

        // then
        assertThat(companyRes1).isNotEqualTo(companyRes2)
    }
}
