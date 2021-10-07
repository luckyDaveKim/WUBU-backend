package com.wubu.api.domain.price.minutely

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class MinutelyPriceIdTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var date1: LocalDateTime
    private lateinit var date2: LocalDateTime

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        date1 = LocalDateTime.of(1991, 3, 26, 9, 0)
        date2 = LocalDateTime.of(1991, 3, 27, 9, 1)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyPriceId = MinutelyPriceId(
            companyCode = companyCode1,
            dateTime = date1
        )

        // then
        assertThat(minutelyPriceId).isNotNull
        assertThat(minutelyPriceId.companyCode).isEqualTo(companyCode1)
        assertThat(minutelyPriceId.dateTime).isEqualTo(date1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val minutelyPriceId1 = MinutelyPriceId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyPriceId2 = MinutelyPriceId(
            companyCode = companyCode1,
            dateTime = date1
        )

        // then
        assertThat(minutelyPriceId1).isEqualTo(minutelyPriceId2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val minutelyPriceId1 = MinutelyPriceId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyPriceId2 = MinutelyPriceId(
            companyCode = companyCode2,
            dateTime = date2
        )

        // then
        assertThat(minutelyPriceId1).isNotEqualTo(minutelyPriceId2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val minutelyPriceId1 = MinutelyPriceId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyPriceId2 = MinutelyPriceId(
            companyCode = companyCode1,
            dateTime = date1
        )

        // then
        assertThat(minutelyPriceId1.hashCode()).isEqualTo(minutelyPriceId2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val minutelyPriceId1 = MinutelyPriceId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyPriceId2 = MinutelyPriceId(
            companyCode = companyCode2,
            dateTime = date2
        )

        // then
        assertThat(minutelyPriceId1.hashCode()).isNotEqualTo(minutelyPriceId2.hashCode())
    }
}
