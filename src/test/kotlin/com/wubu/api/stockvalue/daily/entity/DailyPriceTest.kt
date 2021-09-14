package com.wubu.api.stockvalue.daily.entity

import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyPriceTest {

    lateinit var code1: Code
    lateinit var code2: Code
    lateinit var date1: LocalDate
    lateinit var date2: LocalDate
    lateinit var open1: Price
    lateinit var open2: Price
    lateinit var high1: Price
    lateinit var high2: Price
    lateinit var low1: Price
    lateinit var low2: Price
    lateinit var close1: Price
    lateinit var close2: Price
    var diff1: Long = 0
    var diff2: Long = 0
    lateinit var volume1: Volume
    lateinit var volume2: Volume

    @BeforeEach
    fun setUp() {
        code1 = Code("000001")
        code2 = Code("000002")
        date1 = LocalDate.of(1991, 3, 26)
        date2 = LocalDate.of(1991, 3, 27)
        open1 = Price(1)
        open2 = Price(10)
        high1 = Price(2)
        high2 = Price(20)
        low1 = Price(3)
        low2 = Price(30)
        close1 = Price(4)
        close2 = Price(40)
        diff1 = 5L
        diff2 = 50L
        volume1 = Volume(6)
        volume2 = Volume(60)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyPrice = DailyPrice(DailyPriceId(code1, date1), open1, high1, low1, close1, diff1, volume1)

        // then
        assertThat(dailyPrice).isNotNull
        assertThat(dailyPrice.id).isNotNull
        assertThat(dailyPrice.id.code).isEqualTo(code1)
        assertThat(dailyPrice.id.date).isEqualTo(date1)
        assertThat(dailyPrice.open).isEqualTo(open1)
        assertThat(dailyPrice.high).isEqualTo(high1)
        assertThat(dailyPrice.low).isEqualTo(low1)
        assertThat(dailyPrice.close).isEqualTo(close1)
        assertThat(dailyPrice.diff).isEqualTo(diff1)
        assertThat(dailyPrice.volume).isEqualTo(volume1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
                DailyPriceId(code1, date1),
                open1,
                high1,
                low1,
                close1,
                diff1,
                volume1)
        val dailyPrice2 = DailyPrice(
                DailyPriceId(code1, date1),
                open1,
                high1,
                low1,
                close1,
                diff1,
                volume1)

        // then
        assertThat(dailyPrice1).isEqualTo(dailyPrice2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
                DailyPriceId(code1, date1),
                open1,
                high1,
                low1,
                close1,
                diff1,
                volume1)
        val dailyPrice2 = DailyPrice(
                DailyPriceId(code2, date2),
                open2,
                high2,
                low2,
                close2,
                diff2,
                volume2)

        // then
        assertThat(dailyPrice1).isNotEqualTo(dailyPrice2)
    }

    @Test
    fun `HashCode 비교 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
                DailyPriceId(code1, date1),
                open1,
                high1,
                low1,
                close1,
                diff1,
                volume1)
        val dailyPrice2 = DailyPrice(
                DailyPriceId(code1, date1),
                open1,
                high1,
                low1,
                close1,
                diff1,
                volume1)

        // then
        assertThat(dailyPrice1.hashCode()).isEqualTo(dailyPrice2.hashCode())
    }

    @Test
    fun `HashCode 비교 실패 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
                DailyPriceId(code1, date1),
                open1,
                high1,
                low1,
                close1,
                diff1,
                volume1)
        val dailyPrice2 = DailyPrice(
                DailyPriceId(code2, date2),
                open2,
                high2,
                low2,
                close2,
                diff2,
                volume2)

        // then
        assertThat(dailyPrice1.hashCode()).isNotEqualTo(dailyPrice2.hashCode())
    }

}