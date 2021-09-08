package com.wubu.api.stockvalue.daily.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import com.wubu.api.stockvalue.daily.entity.DailyPriceId
import com.wubu.api.stockvalue.daily.price.dto.res.DailyPriceResDto
import com.wubu.api.stockvalue.daily.repository.DailyPriceRepository
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
class DailyPriceServiceTest {

    @Mock
    lateinit var dailyPriceRepository: DailyPriceRepository

    @InjectMocks
    lateinit var dailyPriceService: DailyPriceService

    lateinit var dailyPrice1: DailyPrice
    lateinit var dailyPrice2: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 26)
                ),
                Price(1),
                Price(2),
                Price(3),
                Price(4),
                5,
                Volume(6)
        )

        dailyPrice2 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 27)
                ),
                Price(10),
                Price(20),
                Price(30),
                Price(40),
                50,
                Volume(60)
        )
    }

    @Test
    fun `일별 차트 데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val pagingReqDto = PagingReqDto()
        val dailyPrices = listOf(dailyPrice1, dailyPrice2)
        val dailyPriceResDto = DailyPriceResDto.of(dailyPrices)

        given(dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(code, pagingReqDto.getPageable()))
                .willReturn(dailyPrices)

        // when
        val foundDailyChartsResponseDto = dailyPriceService.findDailyChart(code, pagingReqDto)

        // then
        assertThat(foundDailyChartsResponseDto).isEqualTo(dailyPriceResDto)
    }

}