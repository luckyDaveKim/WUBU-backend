package com.wubu.api.domain.price.minutely

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MinutelyPriceTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var dateTime1: LocalDateTime
    private lateinit var dateTime2: LocalDateTime
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
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        dateTime1 = LocalDateTime.of(1991, 3, 26, 9, 0)
        dateTime2 = LocalDateTime.of(1991, 3, 26, 9, 1)
        open1 = Price(1)
        open2 = Price(10)
        high1 = Price(2)
        high2 = Price(20)
        low1 = Price(3)
        low2 = Price(30)
        close1 = Price(4)
        close2 = Price(40)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyPrice = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode1, dateTime = dateTime1),
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(minutelyPrice).isNotNull
        assertThat(minutelyPrice.id).isNotNull
        assertThat(minutelyPrice.id.companyCode).isEqualTo(companyCode1)
        assertThat(minutelyPrice.id.dateTime).isEqualTo(dateTime1)
        assertThat(minutelyPrice.open).isEqualTo(open1)
        assertThat(minutelyPrice.high).isEqualTo(high1)
        assertThat(minutelyPrice.low).isEqualTo(low1)
        assertThat(minutelyPrice.close).isEqualTo(close1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val minutelyPrice1 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode1, dateTime = dateTime1),
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val minutelyPrice2 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode1, dateTime = dateTime1),
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(minutelyPrice1).isEqualTo(minutelyPrice2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val minutelyPrice1 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode1, dateTime = dateTime1),
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val minutelyPrice2 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode2, dateTime = dateTime2),
            open = open2,
            high = high2,
            low = low2,
            close = close2
        )

        // then
        assertThat(minutelyPrice1).isNotEqualTo(minutelyPrice2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val minutelyPrice1 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode1, dateTime = dateTime1),
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val minutelyPrice2 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode1, dateTime = dateTime1),
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(minutelyPrice1.hashCode()).isEqualTo(minutelyPrice2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val minutelyPrice1 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode1, dateTime = dateTime1),
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val minutelyPrice2 = MinutelyPrice(
            id = MinutelyPriceId(companyCode = companyCode2, dateTime = dateTime2),
            open = open2,
            high = high2,
            low = low2,
            close = close2
        )

        // then
        assertThat(minutelyPrice1.hashCode()).isNotEqualTo(minutelyPrice2.hashCode())
    }
}
