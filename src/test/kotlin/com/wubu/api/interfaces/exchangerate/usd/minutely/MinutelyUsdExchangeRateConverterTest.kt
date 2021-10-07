package com.wubu.api.interfaces.exchangerate.usd.minutely

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateId
import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateConverter.MinutelyUsdExchangeRateToPointConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class MinutelyUsdExchangeRateConverterTest {

    private lateinit var converter: MinutelyUsdExchangeRateToPointConverter

    @BeforeEach
    fun setUp() {
        converter = MinutelyUsdExchangeRateToPointConverter()
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val converter = MinutelyUsdExchangeRateConverter()

        // then
        assertThat(converter).isNotNull
    }

    @Test
    fun `MinutelyUsdExchangeRate to Point 테스트`() {
        // given
        val dateTime = LocalDateTime.of(1991, 3, 26, 9, 0, 0)
        val minutelyUsdExchangeRate = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = dateTime,
                rate = Rate(1.0)
            )
        )

        // when
        val point = converter.convert(minutelyUsdExchangeRate)

        // then
        assertThat(point.x).isEqualTo(dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(point.y).isEqualTo(1.0)
        assertThat(point.z).isEqualTo(0)
        assertThat(point.open).isEqualTo(0)
        assertThat(point.high).isEqualTo(0)
        assertThat(point.low).isEqualTo(0)
        assertThat(point.close).isEqualTo(0)
    }
}
