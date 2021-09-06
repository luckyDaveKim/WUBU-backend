package com.wubu.api.price.daily.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyPriceTest {

    @Test
    fun `생성 테스트`() {
        val code = "000001"
        val date = LocalDate.of(1991, 3, 26)
        val open = 1L
        val high = 2L
        val low = 3L
        val close = 4L
        val diff = 5L
        val volume = 6L
        val dailyPrice = DailyPrice(DailyPriceId(code, date), open, high, low, close, diff, volume)

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

}