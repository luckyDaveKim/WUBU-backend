package com.wubu.api.application.exchangerate.usd.status

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateId
import com.wubu.api.infra.exchangerate.usd.minutely.MinutelyUsdExchangeRateRepository
import com.wubu.api.interfaces.exchangerate.usd.status.UsdExchangeRateStatusDto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class UsdExchangeRateStatusFindServiceTest {

    @Mock
    private lateinit var minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository

    @InjectMocks
    private lateinit var usdExchangeRateStatusFindService: UsdExchangeRateStatusFindService

    @Test
    fun `데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)

        val curDate = date
        val startCurDateTime = curDate.atStartOfDay()
        val endCurDateTime = curDate.plusDays(1).atStartOfDay()

        val beforeDate = date.minusDays(1)
        val startBeforeDateTime = beforeDate.atStartOfDay()
        val endBeforeDateTime = startCurDateTime

        val curRate = Rate(2.0)
        val beforeRate = Rate(1.0)

        given(
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = startCurDateTime,
                beforeDateTime = endCurDateTime
            )
        ).willReturn(
            MinutelyUsdExchangeRate(
                id = MinutelyUsdExchangeRateId(
                    dateTime = LocalDateTime.of(1991, 3, 26, 9, 0),
                    rate = curRate
                )
            )
        )

        given(
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = startBeforeDateTime,
                beforeDateTime = endBeforeDateTime
            )
        ).willReturn(
            MinutelyUsdExchangeRate(
                id = MinutelyUsdExchangeRateId(
                    dateTime = LocalDateTime.of(1991, 3, 25, 9, 0),
                    rate = beforeRate
                )
            )
        )

        // when
        val foundExchangeRateStatusDto = usdExchangeRateStatusFindService.findExchangeRateStatusAtDate(
            date = date
        )

        // then
        val expectedUsdExchangeRateStatusDto = UsdExchangeRateStatusDto(
            curRate = curRate,
            beforeRate = beforeRate
        )
        assertThat(foundExchangeRateStatusDto).isEqualTo(expectedUsdExchangeRateStatusDto)
    }

    @Test
    fun `데이터 조회 실패 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)

        val curDate = date
        val startCurDateTime = curDate.atStartOfDay()
        val endCurDateTime = curDate.plusDays(1).atStartOfDay()

        val beforeDate = date.minusDays(1)
        val startBeforeDateTime = beforeDate.atStartOfDay()
        val endBeforeDateTime = startCurDateTime

        val curRate = Rate(2.0)

        given(
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = startCurDateTime,
                beforeDateTime = endCurDateTime
            )
        ).willReturn(
            MinutelyUsdExchangeRate(
                id = MinutelyUsdExchangeRateId(
                    dateTime = LocalDateTime.of(1991, 3, 26, 9, 0),
                    rate = curRate
                )
            )
        )

        given(
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = startBeforeDateTime,
                beforeDateTime = endBeforeDateTime
            )
        ).willThrow(UsdExchangeRateStatusNotFoundException(curDate))

        // when

        // then
        assertThatThrownBy {
            usdExchangeRateStatusFindService.findExchangeRateStatusAtDate(
                date = date
            )
        }.isInstanceOf(UsdExchangeRateStatusNotFoundException::class.java)
    }
}
