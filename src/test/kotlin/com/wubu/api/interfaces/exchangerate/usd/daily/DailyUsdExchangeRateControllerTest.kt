package com.wubu.api.interfaces.exchangerate.usd.daily

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.exchangerate.usd.daily.DailyUsdExchangeRateFacade
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.Point
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
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

@WebMvcTest(DailyUsdExchangeRateController::class)
internal class DailyUsdExchangeRateControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var dailyUsdExchangeRateFacade: DailyUsdExchangeRateFacade

    private val objectMapper = ObjectMapper()
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
    fun `조회 테스트`() {
        // given
        val points = listOf(point1, point2)
        val dailyUsdExchangeRateRes = DailyUsdExchangeRateRes.of(points)
        val jsonPointResDto = objectMapper.writeValueAsString(dailyUsdExchangeRateRes)

        given(dailyUsdExchangeRateFacade.retrieveDailyExchangeRate(PagingReqDto()))
            .willReturn(dailyUsdExchangeRateRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/exchange-rate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonPointResDto))
            .andDo { print() }
    }

    @Test
    fun `페이징 조회 테스트`() {
        // given
        val page = 2
        val pageSize = 20
        val pagingReqDto = PagingReqDto(page, pageSize)
        val points = listOf(point1, point2)
        val dailyUsdExchangeRateRes = DailyUsdExchangeRateRes.of(points)
        val jsonPointResDto = objectMapper.writeValueAsString(dailyUsdExchangeRateRes)

        given(dailyUsdExchangeRateFacade.retrieveDailyExchangeRate(pagingReqDto))
            .willReturn(dailyUsdExchangeRateRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/exchange-rate")
                .param("page", page.toString())
                .param("pageSize", pageSize.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        verify(dailyUsdExchangeRateFacade)
            .retrieveDailyExchangeRate(pagingReqDto)
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonPointResDto))
            .andDo { print() }
    }
}
