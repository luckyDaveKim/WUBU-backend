package com.wubu.api.stockvalue.daily.price.entity

import com.wubu.api.common.web.model.Code
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyPriceIdTest {

    lateinit var code: Code
    lateinit var date: LocalDate

    @BeforeEach
    fun setUp() {
        code = Code("000001")
        date = LocalDate.of(1991, 3, 26)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailPriceId = DailyPriceId(code, date)

        // then
        assertThat(dailPriceId).isNotNull
        assertThat(dailPriceId.code).isEqualTo(code)
        assertThat(dailPriceId.date).isEqualTo(date)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailPriceId1 = DailyPriceId(code, date)
        val dailPriceId2 = DailyPriceId(code, date)

        // then
        assertThat(dailPriceId1).isEqualTo(dailPriceId2)
    }

}