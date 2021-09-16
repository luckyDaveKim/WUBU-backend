package com.wubu.api.common.web.model

import com.wubu.api.common.error.exception.InvalidLengthCompanyCodeException
import com.wubu.api.common.web.model.CompanyCode.CodeConverter
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CompanyCodeTest {

    @Test
    fun `생성 테스트`() {
        val codeText = "123456"
        val companyCode = CompanyCode("123456")

        assertThat(companyCode.value).isEqualTo(codeText)
    }

    @Test
    fun `코드가 6자리가 아닌 오류 테스트`() {
        assertThatThrownBy { CompanyCode("12345") }
                .isInstanceOf(InvalidLengthCompanyCodeException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        val codeText = "000000"
        val companyCode1 = CompanyCode(codeText)
        val companyCode2 = CompanyCode(codeText)

        assertThat(companyCode1).isEqualTo(companyCode2)
        assertThat(companyCode1.value).isEqualTo(codeText)
        assertThat(companyCode2.value).isEqualTo(codeText)
    }

    @Test
    fun `DB to Object 테스트`() {
        val dbCodeText = "000000"
        val code = CodeConverter().convertToEntityAttribute(dbCodeText)

        assertThat(code).isNotNull
        assertThat(code?.value).isEqualTo(dbCodeText)
    }

    @Test
    fun `DB to Object Null 값 테스트`() {
        val dbCodeText = null
        val code = CodeConverter().convertToEntityAttribute(dbCodeText)

        assertThat(code).isNull()
        assertThat(code?.value).isEqualTo(dbCodeText)
    }

    @Test
    fun `Object to DB 테스트`() {
        val codeText = "000000"
        val companyCode = CompanyCode(codeText)
        val dbCodeText = CodeConverter().convertToDatabaseColumn(companyCode)

        assertThat(dbCodeText).isEqualTo(codeText)
    }

    @Test
    fun `Object to DB Null 값 테스트`() {
        val code = null
        val dbCodeText = CodeConverter().convertToDatabaseColumn(code)

        assertThat(dbCodeText).isNull()
    }

}