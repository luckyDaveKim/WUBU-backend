package com.wubu.api.application.exchangerate.usd.status

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.status.UsdExchangeRateStatusService
import com.wubu.api.interfaces.exchangerate.usd.status.UsdExchangeRateStatusRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class UsdExchangeRateStatusFacadeTest {

    @Mock
    private lateinit var usdExchangeRateStatusService: UsdExchangeRateStatusService

    @InjectMocks
    private lateinit var usdExchangeRateStatusFacade: UsdExchangeRateStatusFacade

    @Test
    fun `환율 상태 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val usdExchangeRateStatusDto = UsdExchangeRateStatusRes(
            curRate = Rate(2.0),
            beforeRate = Rate(1.0)
        )

        given(usdExchangeRateStatusService.retrieveExchangeRateStatusAtDate(date))
            .willReturn(usdExchangeRateStatusDto)

        // when
        val foundMinutelyExchangeRate = usdExchangeRateStatusFacade.retrieveExchangeRateStatusAtDate(date)

        // then
        assertThat(foundMinutelyExchangeRate).isEqualTo(usdExchangeRateStatusDto)
    }
}
