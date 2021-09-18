package com.wubu.api.company.favorite.dto.req

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FavoriteCompanyReqDtoTest {

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
        val favoriteCompanyReqDto = FavoriteCompanyReqDto(companyCodes)

        // then
        assertThat(favoriteCompanyReqDto).isNotNull
        assertThat(favoriteCompanyReqDto.companyCodes).contains(companyCode1, companyCode2)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val companyCodes1 = listOf(companyCode1, companyCode2)

        // when
        val favoriteCompanyReqDto1 = FavoriteCompanyReqDto(companyCodes1)
        val favoriteCompanyReqDto2 = FavoriteCompanyReqDto(companyCodes1)

        // then
        assertThat(favoriteCompanyReqDto1).isEqualTo(favoriteCompanyReqDto2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val companyCodes1 = listOf(companyCode1)
        val companyCodes2 = listOf(companyCode2)

        // when
        val favoriteCompanyReqDto1 = FavoriteCompanyReqDto(companyCodes1)
        val favoriteCompanyReqDto2 = FavoriteCompanyReqDto(companyCodes2)

        // then
        assertThat(favoriteCompanyReqDto1).isNotEqualTo(favoriteCompanyReqDto2)
    }
}
