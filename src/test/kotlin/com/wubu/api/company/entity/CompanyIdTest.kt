package com.wubu.api.company.entity

import com.wubu.api.common.web.model.Code
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CompanyIdTest {

    lateinit var code1: Code
    lateinit var code2: Code

    @BeforeEach
    fun setUp() {
        code1 = Code("000001")
        code2 = Code("000002")
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val companyId = CompanyId(code1)

        // then
        Assertions.assertThat(companyId).isNotNull
        Assertions.assertThat(companyId.code).isEqualTo(code1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(code1)
        val companyId2 = CompanyId(code1)

        // then
        Assertions.assertThat(companyId1).isEqualTo(companyId2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(code1)
        val companyId2 = CompanyId(code2)

        // then
        Assertions.assertThat(companyId1).isNotEqualTo(companyId2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(code1)
        val companyId2 = CompanyId(code1)

        // then
        Assertions.assertThat(companyId1.hashCode()).isEqualTo(companyId2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(code1)
        val companyId2 = CompanyId(code2)

        // then
        Assertions.assertThat(companyId1.hashCode()).isNotEqualTo(companyId2.hashCode())
    }

}