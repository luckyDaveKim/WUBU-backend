package com.wubu.api.application.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRateId
import com.wubu.api.infra.exchangerate.usd.daily.DailyUsdExchangeRateRepository
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateConverter.DailyUsdExchangeRateToPointConverter
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

@ExtendWith(MockitoExtension::class)
class DailyUsdExchangeRateFindServiceTest {

    @Mock
    private lateinit var dailyUsdExchangeRateRepository: DailyUsdExchangeRateRepository

    @Spy
    private lateinit var dailyUsdExchangeRateToPointConverter: DailyUsdExchangeRateToPointConverter

    @InjectMocks
    private lateinit var dailyUsdExchangeRateFindService: DailyUsdExchangeRateFindService

    private lateinit var dailyUsdExchangeRate1: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate2: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate3: DailyUsdExchangeRate

    @BeforeEach
    fun setUp() {
        dailyUsdExchangeRate1 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 25),
                rate = Rate(1.1)
            )
        )
        dailyUsdExchangeRate2 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 26),
                rate = Rate(2.2)
            )
        )
        dailyUsdExchangeRate3 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 27),
                rate = Rate(3.3)
            )
        )
    }

    @Test
    fun `데이터 조회 테스트`() {
        // given
        val pagingReqDto = PagingReqDto()
        val dailyUsdExchangeRates = listOf(dailyUsdExchangeRate1, dailyUsdExchangeRate2, dailyUsdExchangeRate3)
        val reversedDailyUsdExchangeRates = dailyUsdExchangeRates.reversed()
        val pointResDto = PointResDto.of(
            dailyUsdExchangeRates.map(dailyUsdExchangeRateToPointConverter::convert)
                .toList()
        )

        given(dailyUsdExchangeRateRepository.findAllByOrderById_DateDesc(pagingReqDto.getPageable()))
            .willReturn(reversedDailyUsdExchangeRates)

        // when
        val foundDailyUsdExchangeRates = dailyUsdExchangeRateFindService.findDailyExchangeRate(pagingReqDto)

        // then
        assertThat(foundDailyUsdExchangeRates).isNotNull
        assertThat(foundDailyUsdExchangeRates).isEqualTo(pointResDto)
    }
}
