package com.wubu.api.domain.exchangerate.usd.status

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateId
import com.wubu.api.interfaces.exchangerate.usd.status.UsdExchangeRateStatusRes
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
internal class UsdExchangeRateStatusServiceImplTest {

    @Mock
    private lateinit var usdExchangeRateStatusReader: UsdExchangeRateStatusReader

    @InjectMocks
    private lateinit var usdExchangeRateStatusService: UsdExchangeRateStatusServiceImpl

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
        val beforeDate = LocalDate.of(1991, 3, 25)

        given(usdExchangeRateStatusReader.getLastExchangeRateAtDate(date))
            .willReturn(minutelyUsdExchangeRate1)
        given(usdExchangeRateStatusReader.getLastExchangeRateAtDate(beforeDate))
            .willReturn(minutelyUsdExchangeRate2)

        // when
        val foundMinutelyUsdExchangeRates = usdExchangeRateStatusService.retrieveExchangeRateStatusAtDate(date)

        // then
        val usdExchangeRateStatusDto = UsdExchangeRateStatusRes(
            curRate = minutelyUsdExchangeRate1.id.rate,
            beforeRate = minutelyUsdExchangeRate2.id.rate
        )
        assertThat(foundMinutelyUsdExchangeRates).isNotNull
        assertThat(foundMinutelyUsdExchangeRates).isEqualTo(usdExchangeRateStatusDto)
    }
}
