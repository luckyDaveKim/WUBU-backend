package com.wubu.api.common.web.model

import com.wubu.api.common.error.exception.InvalidLengthCompanyCodeException
import com.wubu.api.common.web.model.CompanyCode.CodeConverter
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CompanyCodeTest {

    @Test
    fun `생성 테스트`() {
        // given
        val codeText = "123456"

        // when
        val companyCode = CompanyCode("123456")

        // then
        assertThat(companyCode.value).isEqualTo(codeText)
    }

    @Test
    fun `코드가 6자리가 아닌 오류 테스트`() {
        // given
        val companyCOdeValue = "12345"

        // when

        // then
        assertThatThrownBy { CompanyCode(companyCOdeValue) }
            .isInstanceOf(InvalidLengthCompanyCodeException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val codeText = "000000"

        // when
        val companyCode1 = CompanyCode(codeText)
        val companyCode2 = CompanyCode(codeText)

        // then
        assertThat(companyCode1).isEqualTo(companyCode2)
    }

    @Test
    fun `DB to Object 테스트`() {
        // given
        val dbCodeText = "000000"

        // when
        val code = CodeConverter().convertToEntityAttribute(dbCodeText)

        // then
        assertThat(code).isNotNull
        assertThat(code?.value).isEqualTo(dbCodeText)
    }

    @Test
    fun `DB to Object Null 값 테스트`() {
        // given
        val dbCodeText = null

        // when
        val code = CodeConverter().convertToEntityAttribute(dbCodeText)

        // then
        assertThat(code).isNull()
        assertThat(code?.value).isEqualTo(dbCodeText)
    }

    @Test
    fun `Object to DB 테스트`() {
        // given
        val codeText = "000000"
        val companyCode = CompanyCode(codeText)

        // when
        val dbCodeText = CodeConverter().convertToDatabaseColumn(companyCode)

        // then
        assertThat(dbCodeText).isEqualTo(codeText)
    }

    @Test
    fun `Object to DB Null 값 테스트`() {
        // given
        val code = null

        // when
        val dbCodeText = CodeConverter().convertToDatabaseColumn(code)

        // then
        assertThat(dbCodeText).isNull()
    }
}
