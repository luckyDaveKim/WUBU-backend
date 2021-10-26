package com.wubu.api.infra.exchangerate.usd.status

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateId
import com.wubu.api.infra.exchangerate.usd.minutely.MinutelyUsdExchangeRateRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class UsdExchangeRateStatusReaderImplTest {

    @Mock
    private lateinit var minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository

    @InjectMocks
    private lateinit var usdExchangeRateStatusReader: UsdExchangeRateStatusReaderImpl

    @Test
    fun `데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val startDateTime = date.atStartOfDay()
        val endDateTime = date.plusDays(1).atStartOfDay()

        val minutelyUsdExchangeRate = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0, 0),
                rate = Rate(1.0)
            )
        )

        given(
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = startDateTime,
                beforeDateTime = endDateTime
            )
        ).willReturn(minutelyUsdExchangeRate)

        // when
        val exchangeRate = usdExchangeRateStatusReader.getLastExchangeRateAtDate(date)

        // then
        Assertions.assertThat(exchangeRate).isNotNull
        Assertions.assertThat(exchangeRate).isEqualTo(minutelyUsdExchangeRate)
    }
}
