package com.wubu.api.interfaces.price.daily

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.price.daily.DailyPriceFacade
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
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

@WebMvcTest(DailyPriceController::class)
internal class DailyPriceControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var dailyPriceFacade: DailyPriceFacade

    private val objectMapper = ObjectMapper()
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
        val points = listOf(point1, point2)
        val dailyPriceRes = DailyPriceRes.of(points)
        val jsonDailyPricesResDto = objectMapper.writeValueAsString(dailyPriceRes)

        given(dailyPriceFacade.retrieveDailyPrices(companyCode, PagingReqDto()))
            .willReturn(dailyPriceRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/price/companies/{companyCode}", companyCode.value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonDailyPricesResDto))
            .andDo { print() }
    }

    @Test
    fun `페이징 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val points = listOf(point1, point2)
        val dailyPriceRes = DailyPriceRes.of(points)
        val jsonDailyPricesResDto = objectMapper.writeValueAsString(dailyPriceRes)

        given(dailyPriceFacade.retrieveDailyPrices(companyCode, PagingReqDto()))
            .willReturn(dailyPriceRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/price/companies/{companyCode}", companyCode.value)
                .param("page", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().string(jsonDailyPricesResDto))
            .andDo { print() }
    }
}
