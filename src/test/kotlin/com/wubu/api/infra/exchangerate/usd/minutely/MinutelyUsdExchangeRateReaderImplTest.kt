package com.wubu.api.infra.exchangerate.usd.minutely

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class MinutelyUsdExchangeRateReaderImplTest {

    @Mock
    private lateinit var minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository

    @InjectMocks
    private lateinit var minutelyUsdExchangeRateReader: MinutelyUsdExchangeRateReaderImpl

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
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()
        val minutelyUsdExchangeRates = listOf(minutelyUsdExchangeRate1, minutelyUsdExchangeRate2)
        val reversedDailyUsdExchangeRates = minutelyUsdExchangeRates.reversed()

        given(
            minutelyUsdExchangeRateRepository.findAllById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
        ).willReturn(reversedDailyUsdExchangeRates)

        // when
        val foundDailyUsdExchangeRates = minutelyUsdExchangeRateReader.getMinutelyExchangeRates(date)

        // then
        assertThat(foundDailyUsdExchangeRates).isNotNull
        assertThat(foundDailyUsdExchangeRates).isEqualTo(minutelyUsdExchangeRates)
    }
}
