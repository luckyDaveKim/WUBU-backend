package com.wubu.api.domain.exchangerate.usd.minutely

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateConverter.MinutelyUsdExchangeRateToPointConverter
import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class MinutelyUsdExchangeRateServiceImplTest {

    @Mock
    private lateinit var minutelyUsdExchangeRateReader: MinutelyUsdExchangeRateReader

    @Spy
    private lateinit var minutelyUsdExchangeRateToPointConverter: MinutelyUsdExchangeRateToPointConverter

    @InjectMocks
    private lateinit var minutelyUsdExchangeRateService: MinutelyUsdExchangeRateServiceImpl

    private lateinit var minutelyUsdExchangeRate1: MinutelyUsdExchangeRate
    private lateinit var minutelyUsdExchangeRate2: MinutelyUsdExchangeRate

    @BeforeEach
    fun setUp() {
        minutelyUsdExchangeRate1 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0, 0),
                rate = Rate(1.0)
            )
        )
        minutelyUsdExchangeRate2 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 10, 0, 0),
                rate = Rate(2.0)
            )
        )
    }

    @Test
    fun `데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val minutelyUsdExchangeRates = listOf(minutelyUsdExchangeRate1, minutelyUsdExchangeRate2)
        val minutelyUsdExchangeRateRes = MinutelyUsdExchangeRateRes.of(
            minutelyUsdExchangeRates.map(minutelyUsdExchangeRateToPointConverter::convert)
        )

        given(minutelyUsdExchangeRateReader.findMinutelyExchangeRates(date))
            .willReturn(minutelyUsdExchangeRates)

        // when
        val foundMinutelyUsdExchangeRates = minutelyUsdExchangeRateService.getMinutelyExchangeRate(date)

        // then
        assertThat(foundMinutelyUsdExchangeRates).isNotNull
        assertThat(foundMinutelyUsdExchangeRates).isEqualTo(minutelyUsdExchangeRateRes)
    }
}
