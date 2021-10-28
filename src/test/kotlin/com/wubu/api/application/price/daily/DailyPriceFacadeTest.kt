package com.wubu.api.application.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.price.daily.DailyPriceService
import com.wubu.api.interfaces.price.daily.DailyPriceRes
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
internal class DailyPriceFacadeTest {

    @Mock
    private lateinit var dailyPriceService: DailyPriceService

    @InjectMocks
    private lateinit var dailyPriceFacade: DailyPriceFacade

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
    fun `데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val pagingReqDto = PagingReqDto()
        val points = listOf(point1, point2)
        val dailyPriceRes = DailyPriceRes.of(points)

        given(
            dailyPriceService.retrieveDailyPrices(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(dailyPriceRes)

        // when
        val foundDailyPriceRes = dailyPriceFacade.retrieveDailyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundDailyPriceRes).isEqualTo(dailyPriceRes)
    }

    @Test
    fun `주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.of(2021, 10, 28)
        val points = listOf(point1, point2)
        val weekPriceRes = DailyPriceRes.of(points)

        given(
            dailyPriceService.retrieveThisWeekPrices(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(weekPriceRes)

        // when
        val foundThisWeekPriceRes = dailyPriceFacade.retrieveThisWeekPrices(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundThisWeekPriceRes).isEqualTo(weekPriceRes)
    }

    @Test
    fun `default date 주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.now()
        val points = listOf(point1, point2)
        val weekPriceRes = DailyPriceRes.of(points)

        given(
            dailyPriceService.retrieveThisWeekPrices(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(weekPriceRes)

        // when
        val foundThisWeekPriceRes = dailyPriceFacade.retrieveThisWeekPrices(companyCode)

        // then
        assertThat(foundThisWeekPriceRes).isEqualTo(weekPriceRes)
    }
}
