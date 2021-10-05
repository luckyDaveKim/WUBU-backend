package com.wubu.api.interfaces.exchangerate.usd.daily

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRateId
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateConverter.DailyUsdExchangeRateToPointConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

class DailyUsdExchangeRateConverterTest {

    private lateinit var converter: DailyUsdExchangeRateToPointConverter

    @BeforeEach
    fun setUp() {
        converter = DailyUsdExchangeRateToPointConverter()
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val converter = DailyUsdExchangeRateConverter()

        // then
        assertThat(converter).isNotNull
    }

    @Test
    fun `MinutelyUsdExchangeRate to Point 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val dailyUsdExchangeRate = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = date,
                rate = Rate(1.2)
            )
        )

        // when
        val point = converter.convert(dailyUsdExchangeRate)

        // then
        assertThat(point.x).isEqualTo(date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(point.y).isEqualTo(1.2)
        assertThat(point.z).isEqualTo(0)
        assertThat(point.open).isEqualTo(0)
        assertThat(point.high).isEqualTo(0)
        assertThat(point.low).isEqualTo(0)
        assertThat(point.close).isEqualTo(0)
    }
}
