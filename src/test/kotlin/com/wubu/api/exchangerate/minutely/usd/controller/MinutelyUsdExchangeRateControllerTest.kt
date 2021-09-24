package com.wubu.api.exchangerate.minutely.usd.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.exchangerate.minutely.usd.binding.MinutelyUsdExchangeRateConverter.MinutelyUsdExchangeRateToPointConverter
import com.wubu.api.exchangerate.minutely.usd.entity.MinutelyUsdExchangeRate
import com.wubu.api.exchangerate.minutely.usd.entity.MinutelyUsdExchangeRateId
import com.wubu.api.exchangerate.minutely.usd.service.MinutelyUsdExchangeRateFindService
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
import java.time.LocalDateTime

@WebMvcTest(MinutelyUsdExchangeRateController::class)
class MinutelyUsdExchangeRateControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var minutelyUsdExchangeRateFindService: MinutelyUsdExchangeRateFindService

    private val minutelyUsdExchangeRateToPointConverter = MinutelyUsdExchangeRateToPointConverter()
    private val objectMapper = ObjectMapper()
    private lateinit var minutelyUsdExchangeRate1: MinutelyUsdExchangeRate
    private lateinit var minutelyUsdExchangeRate2: MinutelyUsdExchangeRate

    @BeforeEach
    fun setUp() {
        minutelyUsdExchangeRate1 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0, 0),
                rate = Rate(1.0)
            )
        )
        minutelyUsdExchangeRate2 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 10, 0, 0),
                rate = Rate(2.0)
            )
        )
    }

    @Test
    fun `특정일 데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(minutelyUsdExchangeRate1, minutelyUsdExchangeRate2)
            .map(minutelyUsdExchangeRateToPointConverter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonPointResDto = objectMapper.writeValueAsString(pointResDto)

        given(
            minutelyUsdExchangeRateFindService.findMinutelyExchangeRateAtDate(date = date)
        ).willReturn(pointResDto)

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
