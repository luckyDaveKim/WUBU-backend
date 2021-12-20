package com.wubu.api.common.web.model

import com.wubu.api.common.web.model.stockvalue.Price
import org.assertj.core.api.Assertions
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
        Assertions.assertThat(ohlc).isNotNull
        Assertions.assertThat(ohlc.open).isEqualTo(open)
        Assertions.assertThat(ohlc.high).isEqualTo(high)
        Assertions.assertThat(ohlc.low).isEqualTo(low)
        Assertions.assertThat(ohlc.close).isEqualTo(close)
    }
}
