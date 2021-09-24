package com.wubu.api.common.web.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BalanceTest {

    @Test
    fun `Equal 비교 테스트`() {
        // given
        val num1 = 0
        val num2 = 0

        // when
        val balance = Balance.compare(num1, num2)

        // then
        assertThat(balance).isEqualTo(Balance.EQUAL)
    }

    @Test
    fun `Up 비교 테스트`() {
        // given
        val num1 = 1
        val num2 = 0

        // when
        val balance = Balance.compare(num1, num2)

        // then
        assertThat(balance).isEqualTo(Balance.UP)
    }

    @Test
    fun `Down 비교 테스트`() {
        // given
        val num1 = -1
        val num2 = 0

        // when
        val balance = Balance.compare(num1, num2)

        // then
        assertThat(balance).isEqualTo(Balance.DOWN)
    }

    @Test
    fun `다중 타입 비교 테스트`() {
        // given
        val num1 = 1
        val num2 = 2.2

        // when
        val balance = Balance.compare(num1, num2)

        // then
        assertThat(balance).isEqualTo(Balance.DOWN)
    }
}
