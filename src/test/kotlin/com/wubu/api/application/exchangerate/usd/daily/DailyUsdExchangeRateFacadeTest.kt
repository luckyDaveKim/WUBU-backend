package com.wubu.api.application.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRateService
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class DailyUsdExchangeRateFacadeTest {

    @Mock
    private lateinit var dailyUsdExchangeRateService: DailyUsdExchangeRateService

    @InjectMocks
    private lateinit var dailyUsdExchangeRateFacade: DailyUsdExchangeRateFacade

    private lateinit var point1: Point
    private lateinit var point2: Point

    @BeforeEach
    fun setUp() {
        point1 = Point(
            x = 1,
            y = 2,
            z = 3,
            open = 4,
            high = 5,
            low = 6,
            close = 7
        )
        point2 = Point(
            x = 10,
            y = 20,
            z = 30,
            open = 40,
            high = 50,
            low = 60,
            close = 70
        )
    }

    @Test
    fun `회사 리스트 조회 테스트`() {
        // given
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

        val points = listOf(point1, point2)
        val dailyUsdExchangeRateRes = DailyUsdExchangeRateRes.of(points)

        given(dailyUsdExchangeRateService.getDailyExchangeRate(pagingReqDto))
            .willReturn(dailyUsdExchangeRateRes)

        // when
        val foundDailyExchangeRate = dailyUsdExchangeRateFacade.retrieveDailyExchangeRate(pagingReqDto)

        // then
        assertThat(foundDailyExchangeRate).isEqualTo(dailyUsdExchangeRateRes)
    }
}
