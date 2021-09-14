package com.wubu.api.stockvalue.daily.price.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.stockvalue.daily.price.binding.DailyPriceConverter.DailyPriceToPointConverter
import com.wubu.api.stockvalue.daily.price.entity.DailyPrice
import com.wubu.api.stockvalue.daily.price.entity.DailyPriceId
import com.wubu.api.stockvalue.daily.price.repository.DailyPriceRepository
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
class DailyPriceFindServiceTest {

    @Mock
    lateinit var dailyPriceRepository: DailyPriceRepository

    @Spy
    lateinit var dailyPriceToPointConverter: DailyPriceToPointConverter

    @InjectMocks
    lateinit var dailyPriceFindService: DailyPriceFindService

    lateinit var dailyPrice1: DailyPrice
    lateinit var dailyPrice2: DailyPrice
    lateinit var dailyPrice3: DailyPrice
    lateinit var dailyPrice4: DailyPrice
    lateinit var dailyPrice5: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 24)
                ),
                Price(1),
                Price(2),
                Price(3),
                Price(4)
        )

        dailyPrice2 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 25)
                ),
                Price(10),
                Price(20),
                Price(30),
                Price(40)
        )

        dailyPrice3 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 26)
                ),
                Price(100),
                Price(200),
                Price(300),
                Price(400)
        )

        dailyPrice4 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 27)
                ),
                Price(1000),
                Price(2000),
                Price(3000),
                Price(4000)
        )

        dailyPrice5 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 28)
                ),
                Price(1),
                Price(2),
                Price(3),
                Price(4)
        )
    }

    @Test
    fun `일별 데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val pagingReqDto = PagingReqDto()
        val reversedDailyPrices = listOf(dailyPrice4, dailyPrice3, dailyPrice2, dailyPrice1)
        val dailyPrices = reversedDailyPrices.reversed()
        val points = dailyPrices.map(dailyPriceToPointConverter::convert)
                .toList()
        val pointResDto = PointResDto.of(points)

        given(dailyPriceRepository.findAllByIdCodeOrderByIdDateDesc(code, pagingReqDto.getPageable()))
                .willReturn(reversedDailyPrices)

        // when
        val foundDailyChartsResponseDto = dailyPriceFindService.findDailyStockValue(code, pagingReqDto)

        // then
        assertThat(foundDailyChartsResponseDto).isEqualTo(pointResDto)
    }

    @Test
    fun `주 데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val date = LocalDate.now()
        val mondayDate = DateUtil.getStartDateOfWeek(date)
        val dailyPrices = listOf(dailyPrice2, dailyPrice3)
        val points = dailyPrices.map(dailyPriceToPointConverter::convert)
                .toList()
        val pointResDto = PointResDto.of(points)

        given(dailyPriceRepository.findAllByIdCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
                code,
                mondayDate))
                .willReturn(dailyPrices)

        // when
        val foundDailyChartsResponseDto = dailyPriceFindService.findThisWeekStockValue(code, date)

        // then
        assertThat(foundDailyChartsResponseDto).isEqualTo(pointResDto)
    }

    @Test
    fun `default date 주 데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val date = LocalDate.now()
        val thisMondayDate = DateUtil.getStartDateOfWeek(date)
        val thisMondayPrice = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        thisMondayDate
                ),
                Price(1),
                Price(2),
                Price(3),
                Price(4)
        )
        val thisTuesdayPrice = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        thisMondayDate.plusDays(1)
                ),
                Price(10),
                Price(20),
                Price(30),
                Price(40)
        )
        val reversedDailyPrices = listOf(thisTuesdayPrice, thisMondayPrice)
        val dailyPrices = reversedDailyPrices.reversed()
        val points = dailyPrices.map(dailyPriceToPointConverter::convert)
                .toList()
        val pointResDto = PointResDto.of(points)

        given(dailyPriceRepository.findAllByIdCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
                code,
                thisMondayDate))
                .willReturn(dailyPrices)

        // when
        val foundDailyChartsResponseDto = dailyPriceFindService.findThisWeekStockValue(code)

        // then
        assertThat(foundDailyChartsResponseDto).isEqualTo(pointResDto)
    }

}