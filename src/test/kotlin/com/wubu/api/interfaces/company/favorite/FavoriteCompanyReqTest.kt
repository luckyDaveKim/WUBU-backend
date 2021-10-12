package com.wubu.api.interfaces.company.favorite

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FavoriteCompanyReqTest {

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
        val companyCodes = listOf(companyCode1, companyCode2)

        // when
        val favoriteCompanyReq = FavoriteCompanyReq(companyCodes)

        // then
        assertThat(favoriteCompanyReq).isNotNull
        assertThat(favoriteCompanyReq.companyCodes).contains(companyCode1, companyCode2)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val companyCodes1 = listOf(companyCode1, companyCode2)

        // when
        val favoriteCompanyReq1 = FavoriteCompanyReq(companyCodes1)
        val favoriteCompanyReq2 = FavoriteCompanyReq(companyCodes1)

        // then
        assertThat(favoriteCompanyReq1).isEqualTo(favoriteCompanyReq2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val companyCodes1 = listOf(companyCode1)
        val companyCodes2 = listOf(companyCode2)

        // when
        val favoriteCompanyReq1 = FavoriteCompanyReq(companyCodes1)
        val favoriteCompanyReq2 = FavoriteCompanyReq(companyCodes2)

        // then
        assertThat(favoriteCompanyReq1).isNotEqualTo(favoriteCompanyReq2)
    }
}
