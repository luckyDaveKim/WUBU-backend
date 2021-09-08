package com.wubu.api.stockvalue.daily.entity

import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyPriceTest {

    lateinit var code: Code
    lateinit var date: LocalDate
    lateinit var open: Price
    lateinit var high: Price
    lateinit var low: Price
    lateinit var close: Price
    var diff: Long = 0
    lateinit var volume: Volume

    @BeforeEach
    fun setUp() {
        code = Code("000001")
        date = LocalDate.of(1991, 3, 26)
        open = Price(1)
        high = Price(2)
        low = Price(3)
        close = Price(4)
        diff = 5L
        volume = Volume(6)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyPrice = DailyPrice(DailyPriceId(code, date), open, high, low, close, diff, volume)

        // then
        assertThat(dailyPrice).isNotNull
        assertThat(dailyPrice.id).isNotNull
        assertThat(dailyPrice.id.code).isEqualTo(code)
        assertThat(dailyPrice.id.date).isEqualTo(date)
        assertThat(dailyPrice.open).isEqualTo(open)
        assertThat(dailyPrice.high).isEqualTo(high)
        assertThat(dailyPrice.low).isEqualTo(low)
        assertThat(dailyPrice.close).isEqualTo(close)
        assertThat(dailyPrice.diff).isEqualTo(diff)
        assertThat(dailyPrice.volume).isEqualTo(volume)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(DailyPriceId(code, date), open, high, low, close, diff, volume)
        val dailyPrice2 = DailyPrice(DailyPriceId(code, date), open, high, low, close, diff, volume)

        // then
        assertThat(dailyPrice1).isEqualTo(dailyPrice2)
    }

}