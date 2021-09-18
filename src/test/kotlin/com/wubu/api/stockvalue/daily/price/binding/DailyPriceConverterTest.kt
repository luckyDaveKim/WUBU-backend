package com.wubu.api.stockvalue.daily.price.binding

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.stockvalue.daily.price.binding.DailyPriceConverter.DailyPriceToPointConverter
import com.wubu.api.stockvalue.daily.price.entity.DailyPrice
import com.wubu.api.stockvalue.daily.price.entity.DailyPriceId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

class DailyPriceConverterTest {

    private lateinit var dailyPriceToPointConverter: DailyPriceToPointConverter

    @BeforeEach
    fun setUp() {
        dailyPriceToPointConverter = DailyPriceToPointConverter()
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyPriceConverter = DailyPriceConverter()

        // then
        assertThat(dailyPriceConverter).isNotNull
    }

    @Test
    fun `DailyPrice to Point 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 24)
        val open = 1L
        val high = 2L
        val low = 3L
        val close = 4L
        val dailyPriceId = DailyPriceId(
            CompanyCode("000000"),
            date
        )
        val dailyPrice = DailyPrice(
            dailyPriceId,
            Price(open),
            Price(high),
            Price(low),
            Price(close)
        )

        // when
        val point = dailyPriceToPointConverter.convert(dailyPrice)

        // then
        assertThat(point.x).isEqualTo(date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(point.y).isEqualTo(close)
        assertThat(point.z).isEqualTo(0)
        assertThat(point.open).isEqualTo(open)
        assertThat(point.high).isEqualTo(high)
        assertThat(point.low).isEqualTo(low)
        assertThat(point.close).isEqualTo(close)
    }
}
