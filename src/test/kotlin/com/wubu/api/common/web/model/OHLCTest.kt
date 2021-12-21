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
}
