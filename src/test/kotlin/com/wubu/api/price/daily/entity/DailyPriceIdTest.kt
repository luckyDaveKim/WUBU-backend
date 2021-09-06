package com.wubu.api.price.daily.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyPriceIdTest {

    @Test
    fun `생성 테스트`() {
        val code = "000001"
        val date = LocalDate.of(1991, 3, 26)
        val dailPriceId = DailyPriceId(code, date)

        assertThat(dailPriceId).isNotNull
        assertThat(dailPriceId.code).isEqualTo(code)
        assertThat(dailPriceId.date).isEqualTo(date)
    }

}