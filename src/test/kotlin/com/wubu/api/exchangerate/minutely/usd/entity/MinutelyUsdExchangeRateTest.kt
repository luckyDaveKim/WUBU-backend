package com.wubu.api.exchangerate.minutely.usd.entity

import com.wubu.api.common.web.model.exchangerate.Rate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MinutelyUsdExchangeRateTest {

    private lateinit var minutelyUsdExchangeRateId1: MinutelyUsdExchangeRateId
    private lateinit var minutelyUsdExchangeRateId2: MinutelyUsdExchangeRateId

    @BeforeEach
    fun setUp() {
        minutelyUsdExchangeRateId1 = MinutelyUsdExchangeRateId(
            dateTime = LocalDateTime.of(1991, 3, 26, 9, 0, 0),
            rate = Rate(1.0)
        )
        minutelyUsdExchangeRateId2 = MinutelyUsdExchangeRateId(
            dateTime = LocalDateTime.of(1991, 3, 26, 9, 0, 1),
            rate = Rate(2.0)
        )
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRate = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId1
        )

        // then
        assertThat(minutelyUsdExchangeRate).isNotNull
        assertThat(minutelyUsdExchangeRate.id).isEqualTo(minutelyUsdExchangeRateId1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRate1 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId1
        )
        val minutelyUsdExchangeRate2 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId1
        )

        // then
        assertThat(minutelyUsdExchangeRate1).isEqualTo(minutelyUsdExchangeRate2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRate1 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId1
        )
        val minutelyUsdExchangeRate2 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId2
        )

        // then
        assertThat(minutelyUsdExchangeRate1).isNotEqualTo(minutelyUsdExchangeRate2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRate1 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId1
        )
        val minutelyUsdExchangeRate2 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId1
        )

        // then
        assertThat(minutelyUsdExchangeRate1.hashCode()).isEqualTo(minutelyUsdExchangeRate2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRate1 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId1
        )
        val minutelyUsdExchangeRate2 = MinutelyUsdExchangeRate(
            id = minutelyUsdExchangeRateId2
        )

        // then
        assertThat(minutelyUsdExchangeRate1.hashCode()).isNotEqualTo(minutelyUsdExchangeRate2.hashCode())
    }
}
