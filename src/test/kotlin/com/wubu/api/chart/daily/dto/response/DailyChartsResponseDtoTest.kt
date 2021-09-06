package com.wubu.api.chart.daily.dto.response

import com.wubu.api.price.daily.entity.DailyPrice
import com.wubu.api.price.daily.entity.DailyPriceId
import com.wubu.api.price.daily.model.Code
import com.wubu.api.price.daily.model.Price
import com.wubu.api.price.daily.model.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.Timestamp
import java.time.LocalDate

class DailyChartsResponseDtoTest {

    lateinit var dailyPrice1: DailyPrice
    lateinit var dailyPrice2: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(DailyPriceId(Code("000001"), LocalDate.of(1991, 3, 26)), Price(1), Price(2), Price(3), Price(4), 5, Volume(6))
        dailyPrice2 = DailyPrice(DailyPriceId(Code("000001"), LocalDate.of(1991, 3, 27)), Price(10), Price(20), Price(30), Price(40), 50, Volume(60))
    }

    @Test
    fun `Entity to Dto 테스트`() {
        val dailyChartsResponseDto = DailyChartsResponseDto.DailyChartDto.of(dailyPrice1)

        assertThat(dailyChartsResponseDto).isNotNull
        assertThat(dailyChartsResponseDto.timestamp).isEqualTo(Timestamp.valueOf(dailyPrice1.id.date.atStartOfDay()))
        assertThat(dailyChartsResponseDto.open).isEqualTo(dailyPrice1.open)
        assertThat(dailyChartsResponseDto.high).isEqualTo(dailyPrice1.high)
        assertThat(dailyChartsResponseDto.low).isEqualTo(dailyPrice1.low)
        assertThat(dailyChartsResponseDto.close).isEqualTo(dailyPrice1.close)
    }

    @Test
    fun `Entity List to Dto 테스트`() {
        val dailyPrices = listOf(dailyPrice1, dailyPrice2)
        val dailyChartsResponseDto = DailyChartsResponseDto.of(dailyPrices)

        assertThat(dailyChartsResponseDto).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart).isNotNull

        assertThat(dailyChartsResponseDto.dailyChart[0]).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart[0].timestamp).isEqualTo(Timestamp.valueOf(dailyPrice1.id.date.atStartOfDay()))
        assertThat(dailyChartsResponseDto.dailyChart[0].open).isEqualTo(dailyPrice1.open)
        assertThat(dailyChartsResponseDto.dailyChart[0].high).isEqualTo(dailyPrice1.high)
        assertThat(dailyChartsResponseDto.dailyChart[0].low).isEqualTo(dailyPrice1.low)
        assertThat(dailyChartsResponseDto.dailyChart[0].close).isEqualTo(dailyPrice1.close)

        assertThat(dailyChartsResponseDto.dailyChart[1]).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart[1].timestamp).isEqualTo(Timestamp.valueOf(dailyPrice2.id.date.atStartOfDay()))
        assertThat(dailyChartsResponseDto.dailyChart[1].open).isEqualTo(dailyPrice2.open)
        assertThat(dailyChartsResponseDto.dailyChart[1].high).isEqualTo(dailyPrice2.high)
        assertThat(dailyChartsResponseDto.dailyChart[1].low).isEqualTo(dailyPrice2.low)
        assertThat(dailyChartsResponseDto.dailyChart[1].close).isEqualTo(dailyPrice2.close)
    }

}