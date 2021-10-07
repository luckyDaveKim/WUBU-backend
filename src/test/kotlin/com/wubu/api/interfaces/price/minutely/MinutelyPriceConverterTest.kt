package com.wubu.api.interfaces.price.minutely

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.domain.price.minutely.MinutelyPrice
import com.wubu.api.domain.price.minutely.MinutelyPriceId
import com.wubu.api.interfaces.price.minutely.MinutelyPriceConverter.MinutelyPriceToPointConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class MinutelyPriceConverterTest {

    private lateinit var minutelyPriceToPointConverter: MinutelyPriceToPointConverter

    @BeforeEach
    fun setUp() {
        minutelyPriceToPointConverter = MinutelyPriceToPointConverter()
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyPriceConverter = MinutelyPriceConverter()

        // then
        assertThat(minutelyPriceConverter).isNotNull
    }

    @Test
    fun `MinutelyPrice to Point 테스트`() {
        // given
        val dateTime = LocalDateTime.of(1991, 3, 26, 9, 0)
        val open = 1L
        val high = 2L
        val low = 3L
        val close = 4L
        val minutelyPriceId = MinutelyPriceId(
            companyCode = CompanyCode("000001"),
            dateTime = dateTime
        )
        val minutelyPrice = MinutelyPrice(
            id = minutelyPriceId,
            open = Price(open),
            high = Price(high),
            low = Price(low),
            close = Price(close)
        )

        // when
        val point = minutelyPriceToPointConverter.convert(minutelyPrice)

        // then
        assertThat(point.x).isEqualTo(dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(point.y).isEqualTo(close)
        assertThat(point.z).isEqualTo(0)
        assertThat(point.open).isEqualTo(open)
        assertThat(point.high).isEqualTo(high)
        assertThat(point.low).isEqualTo(low)
        assertThat(point.close).isEqualTo(close)
    }
}
