package com.wubu.api.company.entity

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CompanyIdTest {

    lateinit var companyCode1: CompanyCode
    lateinit var companyCode2: CompanyCode

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val companyId = CompanyId(companyCode1)

        // then
        Assertions.assertThat(companyId).isNotNull
        Assertions.assertThat(companyId.companyCode).isEqualTo(companyCode1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(companyCode1)
        val companyId2 = CompanyId(companyCode1)

        // then
        Assertions.assertThat(companyId1).isEqualTo(companyId2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(companyCode1)
        val companyId2 = CompanyId(companyCode2)

        // then
        Assertions.assertThat(companyId1).isNotEqualTo(companyId2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(companyCode1)
        val companyId2 = CompanyId(companyCode1)

        // then
        Assertions.assertThat(companyId1.hashCode()).isEqualTo(companyId2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(companyCode1)
        val companyId2 = CompanyId(companyCode2)

        // then
        Assertions.assertThat(companyId1.hashCode()).isNotEqualTo(companyId2.hashCode())
    }
}
