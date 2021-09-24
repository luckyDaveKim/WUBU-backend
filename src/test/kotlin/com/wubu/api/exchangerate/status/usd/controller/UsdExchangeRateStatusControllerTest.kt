package com.wubu.api.exchangerate.status.usd.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.exchangerate.status.usd.dto.UsdExchangeRateStatusDto
import com.wubu.api.exchangerate.status.usd.service.UsdExchangeRateStatusFindService
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

@WebMvcTest(UsdExchangeRateStatusController::class)
class UsdExchangeRateStatusControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var usdExchangeRateStatusFindService: UsdExchangeRateStatusFindService

    private val objectMapper = ObjectMapper()

    @Test
    fun `특정일 데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val exchangeRateStatusDto = UsdExchangeRateStatusDto(
            curRate = Rate(2.0),
            beforeRate = Rate(1.0)
        )
        val jsonPointResDto = objectMapper.writeValueAsString(exchangeRateStatusDto)

        given(
            usdExchangeRateStatusFindService.findExchangeRateStatusAtDate(date = date)
        ).willReturn(exchangeRateStatusDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/status/exchange-rate/{date}", date.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonPointResDto))
            .andDo { print() }
    }
}
