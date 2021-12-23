package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.stockvalue.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StockPriceTest {

    @Test
    fun `생성 테스트`() {
        // given
        val open = Price(1L)
        val high = Price(2L)
        val low = Price(3L)
        val close = Price(4L)

        // when
        val stockPrice = StockPrice(
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(stockPrice).isNotNull
        assertThat(stockPrice.open).isEqualTo(open)
        assertThat(stockPrice.high).isEqualTo(high)
        assertThat(stockPrice.low).isEqualTo(low)
        assertThat(stockPrice.close).isEqualTo(close)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val open = Price(1L)
        val high = Price(2L)
        val low = Price(3L)
        val close = Price(4L)

        // when
        val stockPrice1 = StockPrice(
            open = open,
            high = high,
            low = low,
            close = close
        )
        val stockPrice2 = StockPrice(
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(stockPrice1).isEqualTo(stockPrice2)
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
        val stockPrice1 = StockPrice(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val stockPrice2 = StockPrice(
            open = open2,
            high = high2,
            low = low2,
            close = close2
        )

        // then
        assertThat(stockPrice1).isNotEqualTo(stockPrice2)
    }
}
