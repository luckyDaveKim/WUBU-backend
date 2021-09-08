package com.wubu.api.stockvalue.daily.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import com.wubu.api.stockvalue.daily.entity.DailyPriceId
import com.wubu.api.stockvalue.daily.price.controller.DailyPriceController
import com.wubu.api.stockvalue.daily.price.dto.res.DailyPriceResDto
import com.wubu.api.stockvalue.daily.service.DailyPriceService
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

@WebMvcTest(DailyPriceController::class)
class DailyPriceControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var dailyPriceService: DailyPriceService

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
    fun `데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val dailyPrices = listOf(dailyPrice1, dailyPrice2)
        val dailyPriceResDto = DailyPriceResDto.of(dailyPrices)
        val jsonDailyChartsResponseDto = objectMapper.writeValueAsString(dailyPriceResDto)

        given(dailyPriceService.findDailyChart(code, PagingReqDto()))
                .willReturn(dailyPriceResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
                get("/api/daily/price/companies/{code}", code.value)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )

        resultActions.andExpect { status().isOk }
                .andExpect(content().json(jsonDailyChartsResponseDto))
                .andDo { print() }
    }

    @Test
    fun `페이징 데이터 조회 테스트`() {
        // given
        val code = Code("000000")
        val dailyPrices = listOf(dailyPrice1, dailyPrice2)
        val dailyPriceResDto = DailyPriceResDto.of(dailyPrices)
        val jsonDailyChartsResponseDto = objectMapper.writeValueAsString(dailyPriceResDto)

        given(dailyPriceService.findDailyChart(code, PagingReqDto()))
                .willReturn(dailyPriceResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
                get("/api/daily/price/companies/{code}", code.value)
                        .param("page", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )

        resultActions.andExpect { status().isOk }
                .andExpect(content().string(jsonDailyChartsResponseDto))
                .andDo { print() }
    }

}