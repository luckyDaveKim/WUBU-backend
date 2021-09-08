package com.wubu.api.chart.daily.dto

import com.wubu.api.price.daily.entity.DailyPrice
import com.wubu.api.price.daily.entity.DailyPriceId
import com.wubu.api.price.daily.model.Code
import com.wubu.api.price.daily.model.Price
import com.wubu.api.price.daily.model.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

class DailyChartsDtoTest {

    lateinit var dailyPrice1: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 26)
                ),
                Price(1),
                Price(2),
                Price(3),
                Price(4),
                5,
                Volume(6)
        )
    }

    @Test
    fun `생성 테스트`() {
        // given
        val x = 1L
        val y = Price(10)
        val open = Price(10)
        val high = Price(10)
        val low = Price(10)
        val close = Price(10)

        // when
        val dailyChartDto = DailyChartDto(x, y, open, high, low, close)

        // then
        assertThat(dailyChartDto).isNotNull
        assertThat(dailyChartDto.x).isEqualTo(x)
        assertThat(dailyChartDto.y).isEqualTo(y)
        assertThat(dailyChartDto.open).isEqualTo(open)
        assertThat(dailyChartDto.high).isEqualTo(high)
        assertThat(dailyChartDto.low).isEqualTo(low)
        assertThat(dailyChartDto.close).isEqualTo(close)
    }

    @Test
    fun `Entity to Dto 테스트`() {
        val dailyChartsResponseDto = DailyChartDto.of(dailyPrice1)

        assertThat(dailyChartsResponseDto).isNotNull
        assertThat(dailyChartsResponseDto.x).isEqualTo(dailyPrice1.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(dailyChartsResponseDto.open).isEqualTo(dailyPrice1.open)
        assertThat(dailyChartsResponseDto.high).isEqualTo(dailyPrice1.high)
        assertThat(dailyChartsResponseDto.low).isEqualTo(dailyPrice1.low)
        assertThat(dailyChartsResponseDto.close).isEqualTo(dailyPrice1.close)
    }


}