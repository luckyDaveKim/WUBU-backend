package com.wubu.api.exchangerate.minutely.usd.entity

import com.wubu.api.common.web.model.exchangerate.Rate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MinutelyUsdExchangeRateIdTest {

    private lateinit var dateTime1: LocalDateTime
    private lateinit var dateTime2: LocalDateTime
    private lateinit var rate1: Rate
    private lateinit var rate2: Rate

    @BeforeEach
    fun setUp() {
        dateTime1 = LocalDateTime.of(1991, 3, 26, 9, 0, 0)
        dateTime2 = LocalDateTime.of(1991, 3, 26, 9, 0, 1)
        rate1 = Rate(1.0)
        rate2 = Rate(2.0)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRateId = MinutelyUsdExchangeRateId(
            dateTime = dateTime1,
            rate = rate1
        )

        // then
        assertThat(minutelyUsdExchangeRateId).isNotNull
        assertThat(minutelyUsdExchangeRateId.dateTime).isEqualTo(dateTime1)
        assertThat(minutelyUsdExchangeRateId.rate).isEqualTo(rate1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRateId1 = MinutelyUsdExchangeRateId(
            dateTime = dateTime1,
            rate = rate1
        )
        val minutelyUsdExchangeRateId2 = MinutelyUsdExchangeRateId(
            dateTime = dateTime1,
            rate = rate1
        )

        // then
        assertThat(minutelyUsdExchangeRateId1).isEqualTo(minutelyUsdExchangeRateId2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRateId1 = MinutelyUsdExchangeRateId(
            dateTime = dateTime1,
            rate = rate1
        )
        val minutelyUsdExchangeRateId2 = MinutelyUsdExchangeRateId(
            dateTime = dateTime2,
            rate = rate2
        )

        // then
        assertThat(minutelyUsdExchangeRateId1).isNotEqualTo(minutelyUsdExchangeRateId2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRateId1 = MinutelyUsdExchangeRateId(
            dateTime = dateTime1,
            rate = rate1
        )
        val minutelyUsdExchangeRateId2 = MinutelyUsdExchangeRateId(
            dateTime = dateTime1,
            rate = rate1
        )

        // then
        assertThat(minutelyUsdExchangeRateId1.hashCode()).isEqualTo(minutelyUsdExchangeRateId2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val minutelyUsdExchangeRateId1 = MinutelyUsdExchangeRateId(
            dateTime = dateTime1,
            rate = rate1
        )
        val minutelyUsdExchangeRateId2 = MinutelyUsdExchangeRateId(
            dateTime = dateTime2,
            rate = rate2
        )

        // then
        assertThat(minutelyUsdExchangeRateId1.hashCode()).isNotEqualTo(minutelyUsdExchangeRateId2.hashCode())
    }
}
