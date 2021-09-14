package com.wubu.api.company.entity

import com.wubu.api.common.web.model.Code
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CompanyIdTest {

    lateinit var code: Code

    @BeforeEach
    fun setUp() {
        code = Code("000001")
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val companyId = CompanyId(code)

        // then
        Assertions.assertThat(companyId).isNotNull
        Assertions.assertThat(companyId.code).isEqualTo(code)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val companyId1 = CompanyId(code)
        val companyId2 = CompanyId(code)

        // then
        Assertions.assertThat(companyId1).isEqualTo(companyId2)
    }

}