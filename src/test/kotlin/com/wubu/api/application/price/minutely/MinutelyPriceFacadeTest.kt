package com.wubu.api.application.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.price.minutely.MinutelyPriceService
import com.wubu.api.interfaces.price.minutely.MinutelyPriceRes
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
internal class MinutelyPriceFacadeTest {

    @Mock
    private lateinit var minutelyPriceService: MinutelyPriceService

    @InjectMocks
    private lateinit var minutelyPriceFacade: MinutelyPriceFacade

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
    fun `분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val pagingReqDto = PagingReqDto(
            page = 1,
            pageSize = 2
        )
        val points = listOf(point1, point2)
        val minutelyPriceRes = MinutelyPriceRes.of(points)

        given(
            minutelyPriceService.retrieveMinutelyPrice(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(minutelyPriceRes)

        // when
        val foundPointRes = minutelyPriceFacade.retrieveMinutelyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundPointRes).isNotNull
        assertThat(foundPointRes).isEqualTo(minutelyPriceRes)
    }

    @Test
    fun `특정일 분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(point1, point2)
        val minutelyPriceRes = MinutelyPriceRes.of(points)

        given(
            minutelyPriceService.retrieveMinutelyStockPrice(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(minutelyPriceRes)

        // when
        val foundPointRes = minutelyPriceFacade.retrieveMinutelyPricesAtDate(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundPointRes).isNotNull
        assertThat(foundPointRes).isEqualTo(minutelyPriceRes)
    }
}
