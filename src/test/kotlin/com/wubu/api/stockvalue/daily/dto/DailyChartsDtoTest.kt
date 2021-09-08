package com.wubu.api.stockvalue.daily.dto

import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import com.wubu.api.stockvalue.daily.entity.DailyPriceId
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
    fun `동등성 비교 테스트`() {
        // given
        val x = 1L
        val y = Price(10)
        val open = Price(10)
        val high = Price(10)
        val low = Price(10)
        val close = Price(10)

        // when
        val dailyChartDto1 = DailyChartDto(x, y, open, high, low, close)
        val dailyChartDto2 = DailyChartDto(x, y, open, high, low, close)

        // then
        assertThat(dailyChartDto1).isEqualTo(dailyChartDto2)
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