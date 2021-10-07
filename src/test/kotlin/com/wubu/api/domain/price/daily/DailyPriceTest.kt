package com.wubu.api.domain.price.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class DailyPriceTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var date1: LocalDate
    private lateinit var date2: LocalDate
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
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyPrice = DailyPrice(DailyPriceId(companyCode1, date1), open1, high1, low1, close1)

        // then
        assertThat(dailyPrice).isNotNull
        assertThat(dailyPrice.id).isNotNull
        assertThat(dailyPrice.id.companyCode).isEqualTo(companyCode1)
        assertThat(dailyPrice.id.date).isEqualTo(date1)
        assertThat(dailyPrice.open).isEqualTo(open1)
        assertThat(dailyPrice.high).isEqualTo(high1)
        assertThat(dailyPrice.low).isEqualTo(low1)
        assertThat(dailyPrice.close).isEqualTo(close1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
            DailyPriceId(companyCode1, date1),
            open1,
            high1,
            low1,
            close1
        )
        val dailyPrice2 = DailyPrice(
            DailyPriceId(companyCode1, date1),
            open1,
            high1,
            low1,
            close1
        )

        // then
        assertThat(dailyPrice1).isEqualTo(dailyPrice2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
            DailyPriceId(companyCode1, date1),
            open1,
            high1,
            low1,
            close1
        )
        val dailyPrice2 = DailyPrice(
            DailyPriceId(companyCode2, date2),
            open2,
            high2,
            low2,
            close2
        )

        // then
        assertThat(dailyPrice1).isNotEqualTo(dailyPrice2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
            DailyPriceId(companyCode1, date1),
            open1,
            high1,
            low1,
            close1
        )
        val dailyPrice2 = DailyPrice(
            DailyPriceId(companyCode1, date1),
            open1,
            high1,
            low1,
            close1
        )

        // then
        assertThat(dailyPrice1.hashCode()).isEqualTo(dailyPrice2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val dailyPrice1 = DailyPrice(
            DailyPriceId(companyCode1, date1),
            open1,
            high1,
            low1,
            close1
        )
        val dailyPrice2 = DailyPrice(
            DailyPriceId(companyCode2, date2),
            open2,
            high2,
            low2,
            close2
        )

        // then
        assertThat(dailyPrice1.hashCode()).isNotEqualTo(dailyPrice2.hashCode())
    }
}
