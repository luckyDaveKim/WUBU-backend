package com.wubu.api.domain.exchangerate.usd.daily

import com.wubu.api.common.web.model.exchangerate.Rate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyUsdExchangeRateTest {

    private lateinit var id1: DailyUsdExchangeRateId
    private lateinit var id2: DailyUsdExchangeRateId

    @BeforeEach
    fun setUp() {
        id1 = DailyUsdExchangeRateId(
            date = LocalDate.of(1991, 3, 26),
            rate = Rate(1.1)
        )
        id2 = DailyUsdExchangeRateId(
            date = LocalDate.of(1991, 3, 27),
            rate = Rate(2.2)
        )
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyUsdExchangeRate = DailyUsdExchangeRate(
            id = id1
        )

        // then
        assertThat(dailyUsdExchangeRate).isNotNull
        assertThat(dailyUsdExchangeRate.id).isEqualTo(id1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyUsdExchangeRate1 = DailyUsdExchangeRate(
            id = id1
        )
        val dailyUsdExchangeRate2 = DailyUsdExchangeRate(
            id = id1
        )

        // then
        assertThat(dailyUsdExchangeRate1).isEqualTo(dailyUsdExchangeRate2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val dailyUsdExchangeRate1 = DailyUsdExchangeRate(
            id = id1
        )
        val dailyUsdExchangeRate2 = DailyUsdExchangeRate(
            id = id2
        )

        // then
        assertThat(dailyUsdExchangeRate1).isNotEqualTo(dailyUsdExchangeRate2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val dailyUsdExchangeRate1 = DailyUsdExchangeRate(
            id = id1
        )
        val dailyUsdExchangeRate2 = DailyUsdExchangeRate(
            id = id1
        )

        // then
        assertThat(dailyUsdExchangeRate1.hashCode()).isEqualTo(dailyUsdExchangeRate2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val dailyUsdExchangeRate1 = DailyUsdExchangeRate(
            id = id1
        )
        val dailyUsdExchangeRate2 = DailyUsdExchangeRate(
            id = id2
        )

        // then
        assertThat(dailyUsdExchangeRate1.hashCode()).isNotEqualTo(dailyUsdExchangeRate2.hashCode())
    }
}
