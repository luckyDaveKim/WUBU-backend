package com.wubu.api.stockvalue.daily.price.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.Point
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.stockvalue.daily.price.entity.DailyPrice
import com.wubu.api.stockvalue.daily.price.entity.DailyPriceId
import com.wubu.api.stockvalue.daily.price.service.DailyPriceFindService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.ZoneOffset

@WebMvcTest(DailyPriceController::class)
class DailyPriceControllerTest(
        @Autowired
        private val mockMvc: MockMvc
) {

    @MockBean
    lateinit var dailyPriceFindService: DailyPriceFindService

    private val objectMapper = ObjectMapper()
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
                Price(4)
        )

        dailyPrice2 = DailyPrice(
                DailyPriceId(
                        Code("000000"),
                        LocalDate.of(1991, 3, 27)
                ),
                Price(10),
                Price(20),
                Price(30),
                Price(40)
        )
    }

    @Test
    fun `데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val points = listOf(dailyPrice1, dailyPrice2)
                .map { source ->
                    Point(
                            x = source.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                            y = source.close.value,
                            open = source.open.value,
                            high = source.high.value,
                            low = source.low.value,
                            close = source.close.value
                    )
                }
                .toList()
        val pointResDto = PointResDto.of(points)
        val jsonDailyPricesResponseDto = objectMapper.writeValueAsString(pointResDto)

        given(dailyPriceFindService.findDailyStockValue(code, PagingReqDto()))
                .willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
                get("/api/daily/price/companies/{code}", code.value)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
                .andExpect(content().json(jsonDailyPricesResponseDto))
                .andDo { print() }
    }

    @Test
    fun `페이징 데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val points = listOf(dailyPrice1, dailyPrice2)
                .map { source ->
                    Point(
                            x = source.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                            y = source.close.value,
                            open = source.open.value,
                            high = source.high.value,
                            low = source.low.value,
                            close = source.close.value
                    )
                }
                .toList()
        val pointResDto = PointResDto.of(points)
        val jsonDailyPricesResponseDto = objectMapper.writeValueAsString(pointResDto)

        given(dailyPriceFindService.findDailyStockValue(code, PagingReqDto()))
                .willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
                get("/api/daily/price/companies/{code}", code.value)
                        .param("page", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
                .andExpect(content().string(jsonDailyPricesResponseDto))
                .andDo { print() }
    }

}