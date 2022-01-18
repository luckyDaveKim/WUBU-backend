package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class DailyStockIdTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var date1: LocalDate
    private lateinit var date2: LocalDate

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        date1 = LocalDate.of(1991, 3, 26)
        date2 = LocalDate.of(1991, 3, 27)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyStockId = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )

        // then
        assertThat(dailyStockId).isNotNull
        assertThat(dailyStockId.companyCode).isEqualTo(companyCode1)
        assertThat(dailyStockId.date).isEqualTo(date1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyStockId1 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )
        val dailyStockId2 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )

        // then
        assertThat(dailyStockId1).isEqualTo(dailyStockId2)
    }

    @Test
    fun `다른 companyCode 동등성 비교 실패 테스트`() {
        // given

        // when
        val dailyStockId1 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )
        val dailyStockId2 = DailyStockId(
            companyCode = companyCode2,
            date = date1
        )

        // then
        assertThat(dailyStockId1).isNotEqualTo(dailyStockId2)
    }

    @Test
    fun `다른 date 동등성 비교 실패 테스트`() {
        // given

        // when
        val dailyStockId1 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )
        val dailyStockId2 = DailyStockId(
            companyCode = companyCode1,
            date = date2
        )

        // then
        assertThat(dailyStockId1).isNotEqualTo(dailyStockId2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val dailyStockId1 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )
        val dailyStockId2 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )

        // then
        assertThat(dailyStockId1.hashCode()).isEqualTo(dailyStockId2.hashCode())
    }

    @Test
    fun `다른 companyCode hashCode 비교 실패 테스트`() {
        // given

        // when
        val dailyStockId1 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )
        val dailyStockId2 = DailyStockId(
            companyCode = companyCode2,
            date = date1
        )

        // then
        assertThat(dailyStockId1.hashCode()).isNotEqualTo(dailyStockId2.hashCode())
    }

    @Test
    fun `다른 date hashCode 비교 실패 테스트`() {
        // given

        // when
        val dailyStockId1 = DailyStockId(
            companyCode = companyCode1,
            date = date1
        )
        val dailyStockId2 = DailyStockId(
            companyCode = companyCode1,
            date = date2
        )

        // then
        assertThat(dailyStockId1.hashCode()).isNotEqualTo(dailyStockId2.hashCode())
    }
}
