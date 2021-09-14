package com.wubu.api.stockvalue.daily.price.entity

import com.wubu.api.common.web.model.Code
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyPriceIdTest {

    lateinit var code1: Code
    lateinit var code2: Code
    lateinit var date1: LocalDate
    lateinit var date2: LocalDate

    @BeforeEach
    fun setUp() {
        code1 = Code("000001")
        code2 = Code("000002")
        date1 = LocalDate.of(1991, 3, 26)
        date2 = LocalDate.of(1991, 3, 27)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailPriceId = DailyPriceId(code1, date1)

        // then
        assertThat(dailPriceId).isNotNull
        assertThat(dailPriceId.code).isEqualTo(code1)
        assertThat(dailPriceId.date).isEqualTo(date1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailPriceId1 = DailyPriceId(code1, date1)
        val dailPriceId2 = DailyPriceId(code1, date1)

        // then
        assertThat(dailPriceId1).isEqualTo(dailPriceId2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val dailPriceId1 = DailyPriceId(code1, date1)
        val dailPriceId2 = DailyPriceId(code2, date2)

        // then
        assertThat(dailPriceId1).isNotEqualTo(dailPriceId2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val dailPriceId1 = DailyPriceId(code1, date1)
        val dailPriceId2 = DailyPriceId(code1, date1)

        // then
        assertThat(dailPriceId1).isEqualTo(dailPriceId2)
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val dailPriceId1 = DailyPriceId(code1, date1)
        val dailPriceId2 = DailyPriceId(code2, date2)

        // then
        assertThat(dailPriceId1.hashCode()).isNotEqualTo(dailPriceId2.hashCode())
    }

}