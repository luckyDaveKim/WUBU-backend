package com.wubu.api.application.exchangerate.usd.minutely

import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateService
import com.wubu.api.interfaces.exchangerate.usd.minutely.MinutelyUsdExchangeRateRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class MinutelyUsdExchangeRateFacadeTest {

    @Mock
    private lateinit var minutelyUsdExchangeRateService: MinutelyUsdExchangeRateService

    @InjectMocks
    private lateinit var minutelyUsdExchangeRateFacade: MinutelyUsdExchangeRateFacade

    private lateinit var point1: Point
    private lateinit var point2: Point

    @BeforeEach
    fun setUp() {
        point1 = Point(
            x = 1,
            y = 2
        )
        point2 = Point(
            x = 10,
            y = 20
        )
    }

    @Test
    fun `분별 환율 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(point1, point2)
        val minutelyUsdExchangeRateRes = MinutelyUsdExchangeRateRes.of(points)

        given(minutelyUsdExchangeRateService.getMinutelyExchangeRate(date))
            .willReturn(minutelyUsdExchangeRateRes)

        // when
        val foundMinutelyExchangeRate = minutelyUsdExchangeRateFacade.retrieveMinutelyExchangeRateAtDate(date)

        // then
        assertThat(foundMinutelyExchangeRate).isEqualTo(minutelyUsdExchangeRateRes)
    }
}
