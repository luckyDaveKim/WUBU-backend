package com.wubu.api.interfaces.exchangerate.usd.status

import com.wubu.api.common.web.model.Balance
import com.wubu.api.common.web.model.Percentage
import com.wubu.api.common.web.model.exchangerate.Rate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsdExchangeRateStatusResTest {

    @Test
    fun `생성 테스트`() {
        // given
        val curRate = Rate(1.0)
        val beforeRate = Rate(2.0)

        // when
        val usdExchangeRateStatusRes = UsdExchangeRateStatusRes(
            curRate = curRate,
            beforeRate = beforeRate
        )

        // then
        assertThat(usdExchangeRateStatusRes).isNotNull
        assertThat(usdExchangeRateStatusRes.curRate).isEqualTo(curRate)
        assertThat(usdExchangeRateStatusRes.beforeRate).isEqualTo(beforeRate)
        assertThat(usdExchangeRateStatusRes.comparisonRate).isEqualTo(Rate(1.0))
        assertThat(usdExchangeRateStatusRes.percentage).isEqualTo(Percentage(50.0))
        assertThat(usdExchangeRateStatusRes.balance).isEqualTo(Balance.DOWN)
    }

    @Test
    fun `of 생성 테스트`() {
        // given
        val curRate = Rate(2.0)
        val beforeRate = Rate(1.0)

        // when
        val usdExchangeRateStatusRes = UsdExchangeRateStatusRes.of(
            beforeRate = beforeRate,
            curRate = curRate
        )

        // then
        assertThat(usdExchangeRateStatusRes).isNotNull
        assertThat(usdExchangeRateStatusRes.curRate).isEqualTo(curRate)
        assertThat(usdExchangeRateStatusRes.beforeRate).isEqualTo(beforeRate)
        assertThat(usdExchangeRateStatusRes.comparisonRate).isEqualTo(Rate(1.0))
        assertThat(usdExchangeRateStatusRes.percentage).isEqualTo(Percentage(100.0))
        assertThat(usdExchangeRateStatusRes.balance).isEqualTo(Balance.UP)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val rate1 = Rate(1.0)
        val rate2 = Rate(1.0)

        // then
        assertThat(rate1).isEqualTo(rate2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val rate1 = Rate(1.0)
        val rate2 = Rate(2.0)

        // then
        assertThat(rate1).isNotEqualTo(rate2)
    }
}
