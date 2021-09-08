package com.wubu.api.chart.daily.dto.response

import com.wubu.api.chart.daily.dto.DailyChartDto
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

class DailyChartsResponseDtoTest {

    lateinit var dailyPrice1: DailyPrice
    lateinit var dailyPrice2: DailyPrice

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

        dailyPrice2 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 27)
                ),
                Price(10),
                Price(20),
                Price(30),
                Price(40),
                50,
                Volume(60)
        )
    }

    @Test
    fun `생성 테스트`() {
        // given
        val dailyChartDto1 = DailyChartDto.of(dailyPrice1)
        val dailyChartDto2 = DailyChartDto.of(dailyPrice2)
        val dailyChartDtoList = listOf(dailyChartDto1, dailyChartDto2)

        // when
        val dailyChartsResponseDto = DailyChartsResponseDto(dailyChartDtoList)

        // then
        assertThat(dailyChartsResponseDto).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart[0]).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart[0].x).isEqualTo(dailyPrice1.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(dailyChartsResponseDto.dailyChart[0].open).isEqualTo(dailyPrice1.open)
        assertThat(dailyChartsResponseDto.dailyChart[0].high).isEqualTo(dailyPrice1.high)
        assertThat(dailyChartsResponseDto.dailyChart[0].low).isEqualTo(dailyPrice1.low)
        assertThat(dailyChartsResponseDto.dailyChart[0].close).isEqualTo(dailyPrice1.close)
    }

    @Test
    fun `Entity to Dto 테스트`() {
        val dailyPrices = listOf(dailyPrice1, dailyPrice2)
        val dailyChartsResponseDto = DailyChartsResponseDto.of(dailyPrices)

        assertThat(dailyChartsResponseDto).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart).isNotNull

        assertThat(dailyChartsResponseDto.dailyChart[0]).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart[0].x).isEqualTo(dailyPrice1.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(dailyChartsResponseDto.dailyChart[0].open).isEqualTo(dailyPrice1.open)
        assertThat(dailyChartsResponseDto.dailyChart[0].high).isEqualTo(dailyPrice1.high)
        assertThat(dailyChartsResponseDto.dailyChart[0].low).isEqualTo(dailyPrice1.low)
        assertThat(dailyChartsResponseDto.dailyChart[0].close).isEqualTo(dailyPrice1.close)

        assertThat(dailyChartsResponseDto.dailyChart[1]).isNotNull
        assertThat(dailyChartsResponseDto.dailyChart[1].x).isEqualTo(dailyPrice2.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(dailyChartsResponseDto.dailyChart[1].open).isEqualTo(dailyPrice2.open)
        assertThat(dailyChartsResponseDto.dailyChart[1].high).isEqualTo(dailyPrice2.high)
        assertThat(dailyChartsResponseDto.dailyChart[1].low).isEqualTo(dailyPrice2.low)
        assertThat(dailyChartsResponseDto.dailyChart[1].close).isEqualTo(dailyPrice2.close)
    }

}