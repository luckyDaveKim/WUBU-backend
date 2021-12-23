package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class DailyStockPieceIdTest {

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
        val dailyStockPieceId = DailyStockPieceId(
            companyCode = companyCode1,
            date = date1
        )

        // then
        assertThat(dailyStockPieceId).isNotNull
        assertThat(dailyStockPieceId.companyCode).isEqualTo(companyCode1)
        assertThat(dailyStockPieceId.date).isEqualTo(date1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyStockPieceId1 = DailyStockPieceId(
            companyCode = companyCode1,
            date = date1
        )
        val dailyStockPieceId2 = DailyStockPieceId(
            companyCode = companyCode1,
            date = date1
        )

        // then
        assertThat(dailyStockPieceId1).isEqualTo(dailyStockPieceId2)
    }
}
