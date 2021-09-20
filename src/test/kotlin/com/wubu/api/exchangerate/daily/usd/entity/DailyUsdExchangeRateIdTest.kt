package com.wubu.api.exchangerate.daily.usd.entity

import com.wubu.api.common.web.model.exchangerate.Rate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyUsdExchangeRateIdTest {

    private lateinit var date1: LocalDate
    private lateinit var date2: LocalDate
    private lateinit var rate1: Rate
    private lateinit var rate2: Rate

    @BeforeEach
    fun setUp() {
        date1 = LocalDate.of(1991, 3, 26)
        date2 = LocalDate.of(1991, 3, 27)
        rate1 = Rate(1.0)
        rate2 = Rate(2.0)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val id = DailyUsdExchangeRateId(
            date = date1,
            rate = rate1
        )

        // then
        assertThat(id).isNotNull
        assertThat(id.date).isEqualTo(date1)
        assertThat(id.rate).isEqualTo(rate1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val id1 = DailyUsdExchangeRateId(
            date = date1,
            rate = rate1
        )
        val id2 = DailyUsdExchangeRateId(
            date = date1,
            rate = rate1
        )

        // then
        assertThat(id1).isEqualTo(id2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val id1 = DailyUsdExchangeRateId(
            date = date1,
            rate = rate1
        )
        val id2 = DailyUsdExchangeRateId(
            date = date2,
            rate = rate2
        )

        // then
        assertThat(id1).isNotEqualTo(id2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val id1 = DailyUsdExchangeRateId(
            date = date1,
            rate = rate1
        )
        val id2 = DailyUsdExchangeRateId(
            date = date1,
            rate = rate1
        )

        // then
        assertThat(id1.hashCode()).isEqualTo(id2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val id1 = DailyUsdExchangeRateId(
            date = date1,
            rate = rate1
        )
        val id2 = DailyUsdExchangeRateId(
            date = date2,
            rate = rate2
        )

        // then
        assertThat(id1.hashCode()).isNotEqualTo(id2.hashCode())
    }
}
