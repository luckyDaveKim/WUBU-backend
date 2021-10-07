package com.wubu.api.interfaces.price.minutely

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.price.minutely.MinutelyPriceFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.domain.price.minutely.MinutelyPrice
import com.wubu.api.domain.price.minutely.MinutelyPriceId
import com.wubu.api.interfaces.price.minutely.MinutelyPriceConverter.MinutelyPriceToPointConverter
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

@WebMvcTest(MinutelyController::class)
internal class MinutelyControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var minutelyFindService: MinutelyPriceFindService

    private val minutelyPriceToPointConverter = MinutelyPriceToPointConverter()
    private val objectMapper = ObjectMapper()
    private lateinit var companyCode: CompanyCode
    private lateinit var minutelyPrice1: MinutelyPrice
    private lateinit var minutelyPrice2: MinutelyPrice

    @BeforeEach
    fun setUp() {
        companyCode = CompanyCode("000001")
        minutelyPrice1 = MinutelyPrice(
            id = MinutelyPriceId(
                companyCode = companyCode,
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0)
            ),
            open = Price(1),
            high = Price(2),
            low = Price(3),
            close = Price(4)
        )
        minutelyPrice2 = MinutelyPrice(
            id = MinutelyPriceId(
                companyCode = companyCode,
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 1)
            ),
            open = Price(10),
            high = Price(20),
            low = Price(30),
            close = Price(40)
        )
    }

    @Test
    fun `특정 회사 데이터 조회 테스트`() {
        // given
        val pagingReqDto = PagingReqDto(
            page = 1,
            pageSize = 2
        )

        val points = listOf(minutelyPrice1, minutelyPrice2)
            .map(minutelyPriceToPointConverter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonMinutelyPriceResDto = objectMapper.writeValueAsString(pointResDto)

        given(
            minutelyFindService.findMinutelyStockValue(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/price/companies/{companyCode}", companyCode.value)
                .param("page", "1")
                .param("pageSize", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonMinutelyPriceResDto))
            .andDo { print() }
    }

    @Test
    fun `default 페이징 정보 기반 특정 회사 데이터 조회 테스트`() {
        // given
        val points = listOf(minutelyPrice1, minutelyPrice2)
            .map(minutelyPriceToPointConverter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonMinutelyPriceResDto = objectMapper.writeValueAsString(pointResDto)

        given(
            minutelyFindService.findMinutelyStockValue(
                companyCode = companyCode,
                pagingReqDto = PagingReqDto()
            )
        ).willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/price/companies/{companyCode}", companyCode.value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonMinutelyPriceResDto))
            .andDo { print() }
    }

    @Test
    fun `특정 회사 및 특정일 데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(minutelyPrice1, minutelyPrice2)
            .map(minutelyPriceToPointConverter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonMinutelyPriceResDto = objectMapper.writeValueAsString(pointResDto)

        given(
            minutelyFindService.findMinutelyStockValueAtDate(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/price/{date}/companies/{companyCode}", date, companyCode.value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonMinutelyPriceResDto))
            .andDo { print() }
    }
}
