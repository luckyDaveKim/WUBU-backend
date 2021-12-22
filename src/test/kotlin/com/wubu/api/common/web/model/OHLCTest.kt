package com.wubu.api.common.web.model

import com.wubu.api.common.web.model.stockvalue.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OHLCTest {

    @Test
    fun `생성 테스트`() {
        // given
        val open = Price(1L)
        val high = Price(2L)
        val low = Price(3L)
        val close = Price(4L)

        // when
        val ohlc = OHLC(
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(ohlc).isNotNull
        assertThat(ohlc.open).isEqualTo(open)
        assertThat(ohlc.high).isEqualTo(high)
        assertThat(ohlc.low).isEqualTo(low)
        assertThat(ohlc.close).isEqualTo(close)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val open = Price(1L)
        val high = Price(2L)
        val low = Price(3L)
        val close = Price(4L)

        // when
        val ohlc1 = OHLC(
            open = open,
            high = high,
            low = low,
            close = close
        )
        val ohlc2 = OHLC(
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(ohlc1).isEqualTo(ohlc2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val open1 = Price(1L)
        val high1 = Price(2L)
        val low1 = Price(3L)
        val close1 = Price(4L)

        val open2 = Price(5L)
        val high2 = Price(6L)
        val low2 = Price(7L)
        val close2 = Price(8L)

        // when
        val ohlc1 = OHLC(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val ohlc2 = OHLC(
            open = open2,
            high = high2,
            low = low2,
            close = close2
        )

        // then
        assertThat(ohlc1).isNotEqualTo(ohlc2)
    }
}
