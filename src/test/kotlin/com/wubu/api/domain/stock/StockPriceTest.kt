package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.stockvalue.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class StockPriceTest {

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
        val stockPrice = StockPrice(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(stockPrice).isNotNull
        assertThat(stockPrice.open).isEqualTo(open1)
        assertThat(stockPrice.high).isEqualTo(high1)
        assertThat(stockPrice.low).isEqualTo(low1)
        assertThat(stockPrice.close).isEqualTo(close1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val stockPrice1 = StockPrice(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val stockPrice2 = StockPrice(
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(stockPrice1).isEqualTo(stockPrice2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

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
