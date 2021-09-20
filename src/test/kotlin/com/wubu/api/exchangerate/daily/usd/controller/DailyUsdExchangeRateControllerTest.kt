package com.wubu.api.exchangerate.daily.usd.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.exchangerate.daily.usd.binding.DailyUsdExchangeRateConverter.DailyUsdExchangeRateToPointConverter
import com.wubu.api.exchangerate.daily.usd.entity.DailyUsdExchangeRate
import com.wubu.api.exchangerate.daily.usd.entity.DailyUsdExchangeRateId
import com.wubu.api.exchangerate.daily.usd.service.DailyUsdExchangeRateFindService
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
import java.time.LocalDate

@WebMvcTest(DailyUsdExchangeRateController::class)
class DailyUsdExchangeRateControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var dailyUsdExchangeRateFindService: DailyUsdExchangeRateFindService

    private val converter = DailyUsdExchangeRateToPointConverter()
    private val objectMapper = ObjectMapper()
    private lateinit var dailyUsdExchangeRate1: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate2: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate3: DailyUsdExchangeRate

    @BeforeEach
    fun setUp() {
        dailyUsdExchangeRate1 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 25),
                rate = Rate(1.1)
            )
        )
        dailyUsdExchangeRate2 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 26),
                rate = Rate(2.2)
            )
        )
        dailyUsdExchangeRate3 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 27),
                rate = Rate(3.3)
            )
        )
    }

    @Test
    fun `조회 테스트`() {
        // given
        val points = listOf(dailyUsdExchangeRate1, dailyUsdExchangeRate2, dailyUsdExchangeRate3)
            .map(converter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonPointResDto = objectMapper.writeValueAsString(pointResDto)

        given(
            dailyUsdExchangeRateFindService.findDailyExchangeRate(
                pagingReqDto = PagingReqDto()
            )
        ).willReturn(pointResDto)

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
        val points = listOf(dailyUsdExchangeRate1, dailyUsdExchangeRate2, dailyUsdExchangeRate3)
            .map(converter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonPointResDto = objectMapper.writeValueAsString(pointResDto)

        given(
            dailyUsdExchangeRateFindService.findDailyExchangeRate(
                pagingReqDto = pagingReqDto
            )
        ).willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/exchange-rate")
                .param("page", page.toString())
                .param("pageSize", pageSize.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        verify(dailyUsdExchangeRateFindService)
            .findDailyExchangeRate(pagingReqDto)
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonPointResDto))
            .andDo { print() }
    }
}
