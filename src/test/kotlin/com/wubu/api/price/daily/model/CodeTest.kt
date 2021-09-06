package com.wubu.api.price.daily.model

import com.wubu.api.price.daily.exception.InvalidLengthCodeException
import com.wubu.api.price.daily.model.Code.CodeConverter
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CodeTest {

    @Test
    fun `생성 테스트`() {
        val codeText = "123456"
        val code = Code("123456")

        assertThat(code.value).isEqualTo(codeText)
    }

    @Test
    fun `코드가 6자리가 아닌 오류 테스트`() {
        assertThatThrownBy { Code("12345") }
                .isInstanceOf(InvalidLengthCodeException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        val codeText = "000000"
        val code1 = Code(codeText)
        val code2 = Code(codeText)

        assertThat(code1).isEqualTo(code2)
        assertThat(code1.value).isEqualTo(codeText)
        assertThat(code2.value).isEqualTo(codeText)
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
        val code = Code(codeText)
        val dbCodeText = CodeConverter().convertToDatabaseColumn(code)

        assertThat(dbCodeText).isEqualTo(codeText)
    }

    @Test
    fun `Object to DB Null 값 테스트`() {
        val code = null
        val dbCodeText = CodeConverter().convertToDatabaseColumn(code)

        assertThat(dbCodeText).isNull()
    }

}