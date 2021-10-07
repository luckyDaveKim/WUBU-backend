package com.wubu.api.common.web.model.exchangerate

import com.wubu.api.common.exception.InvalidRateException
import com.wubu.api.common.web.model.SingleValue
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateTest {

    @Test
    fun `생성 테스트`() {
        // given
        val rateValue = 1234.5

        // when
        val rate = Rate(rate = rateValue)

        // then
        assertThat(rate.value).isEqualTo(rateValue)
    }

    @Test
    fun `환율이 음수인 오류 테스트`() {
        // given
        val rateValue = -1.0

        // when

        // then
        Assertions.assertThatThrownBy { Rate(rate = rateValue) }
            .isInstanceOf(InvalidRateException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val rateValue = 1234.5

        // when
        val rate1 = Rate(rate = rateValue)
        val rate2 = Rate(rate = rateValue)

        // then
        assertThat(rate1).isEqualTo(rate2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val rateValue1 = 1.0
        val rateValue2 = 2.0

        // when
        val rate1 = Rate(rate = rateValue1)
        val rate2 = Rate(rate = rateValue2)

        // then
        assertThat(rate1).isNotEqualTo(rate2)
    }

    @Test
    fun `SingleValue 인터페이스 테스트`() {
        // given
        val rateValue = 1.0

        // when
        val rate = Rate(rate = rateValue)

        // then
        assertThat(rate).isInstanceOf(SingleValue::class.java)
    }
}
