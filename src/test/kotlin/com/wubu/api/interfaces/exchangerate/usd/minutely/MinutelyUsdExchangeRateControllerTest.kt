package com.wubu.api.interfaces.exchangerate.usd.minutely

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.exchangerate.usd.minutely.MinutelyUsdExchangeRateFacade
import com.wubu.api.common.web.model.Point
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

@WebMvcTest(MinutelyUsdExchangeRateController::class)
internal class MinutelyUsdExchangeRateControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var minutelyUsdExchangeRateFacade: MinutelyUsdExchangeRateFacade

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
    fun `특정일 데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(point1, point2)
        val minutelyUsdExchangeRateRes = MinutelyUsdExchangeRateRes.of(points)
        val jsonPointResDto = objectMapper.writeValueAsString(minutelyUsdExchangeRateRes)

        given(minutelyUsdExchangeRateFacade.retrieveMinutelyExchangeRateAtDate(date))
            .willReturn(minutelyUsdExchangeRateRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/exchange-rate/{date}", date.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonPointResDto))
            .andDo { print() }
    }
}
