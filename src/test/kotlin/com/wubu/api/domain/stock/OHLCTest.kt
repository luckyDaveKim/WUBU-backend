package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.stockvalue.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OHLCTest {

    private lateinit var open1: Price
    private lateinit var open2: Price
    private lateinit var high1: Price
    private lateinit var high2: Price
    private lateinit var low1: Price
    private lateinit var low2: Price
    private lateinit var close1: Price
    private lateinit var close2: Price

    @BeforeEach
    fun setUp() {
        open1 = Price(1L)
        high1 = Price(2L)
        low1 = Price(3L)
        close1 = Price(4L)

        open2 = Price(10L)
        high2 = Price(20L)
        low2 = Price(30L)
        close2 = Price(40L)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val ohlc = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(ohlc).isNotNull
        assertThat(ohlc.open).isEqualTo(open1)
        assertThat(ohlc.high).isEqualTo(high1)
        assertThat(ohlc.low).isEqualTo(low1)
        assertThat(ohlc.close).isEqualTo(close1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(ohlc1).isEqualTo(ohlc2)
    }

    @Test
    fun `다른 open 동등성 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open2,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(ohlc1).isNotEqualTo(ohlc2)
    }

    @Test
    fun `다른 high 동등성 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high2,
            low = low1,
            close = close1
        )

        // then
        assertThat(ohlc1).isNotEqualTo(ohlc2)
    }

    @Test
    fun `다른 low 동등성 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high1,
            low = low2,
            close = close1
        )

        // then
        assertThat(ohlc1).isNotEqualTo(ohlc2)
    }

    @Test
    fun `다른 close 동등성 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close2
        )

        // then
        assertThat(ohlc1).isNotEqualTo(ohlc2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(ohlc1.hashCode()).isEqualTo(ohlc2.hashCode())
    }

    @Test
    fun `다른 open hashCode 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open2,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(ohlc1.hashCode()).isNotEqualTo(ohlc2.hashCode())
    }

    @Test
    fun `다른 high hashCode 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high2,
            low = low1,
            close = close1
        )

        // then
        assertThat(ohlc1.hashCode()).isNotEqualTo(ohlc2.hashCode())
    }

    @Test
    fun `다른 low hashCode 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high1,
            low = low2,
            close = close1
        )

        // then
        assertThat(ohlc1.hashCode()).isNotEqualTo(ohlc2.hashCode())
    }

    @Test
    fun `다른 close hashCode 비교 실패 테스트`() {
        // given

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close2
        )

        // then
        assertThat(ohlc1.hashCode()).isNotEqualTo(ohlc2.hashCode())
    }
}
