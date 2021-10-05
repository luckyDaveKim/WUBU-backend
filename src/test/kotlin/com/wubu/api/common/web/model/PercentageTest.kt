package com.wubu.api.common.web.model

import com.wubu.api.common.exception.InvalidPercentageException
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PercentageTest {

    @Test
    fun `생성 테스트`() {
        // given
        val percentageValue = 15.5

        // when
        val percentage = Percentage(percentageValue)

        // then
        assertThat(percentage.value).isEqualTo(percentageValue)
    }

    @Test
    fun `백분율이 음수인 오류 테스트`() {
        // given
        val percentageValue = -1.0

        // when

        // then
        Assertions.assertThatThrownBy { Percentage(percentageValue) }
            .isInstanceOf(InvalidPercentageException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val percentageValue = 15.5

        // when
        val percentage1 = Percentage(percentageValue)
        val percentage2 = Percentage(percentageValue)

        // then
        assertThat(percentage1).isEqualTo(percentage2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val percentageValue1 = 1.0
        val percentageValue2 = 22.0

        // when
        val percentage1 = Percentage(percentageValue1)
        val percentage2 = Percentage(percentageValue2)

        // then
        assertThat(percentage1).isNotEqualTo(percentage2)
    }

    @Test
    fun `SingleValue 인터페이스 테스트`() {
        // given
        val percentageValue = 1.0

        // when
        val percentage = Percentage(percentageValue)

        // then
        assertThat(percentage).isInstanceOf(SingleValue::class.java)
    }
}
