package com.wubu.api.interfaces.price.daily

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.price.daily.DailyPriceFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.domain.price.daily.DailyPrice
import com.wubu.api.domain.price.daily.DailyPriceId
import com.wubu.api.interfaces.price.daily.DailyPriceConverter.DailyPriceToPointConverter
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
internal class DailyPriceControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var dailyPriceFindService: DailyPriceFindService

    private val dailyPriceToPointConverter = DailyPriceToPointConverter()
    private val objectMapper = ObjectMapper()
    private lateinit var dailyPrice1: DailyPrice
    private lateinit var dailyPrice2: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(
            DailyPriceId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 26)
            ),
            Price(1),
            Price(2),
            Price(3),
            Price(4)
        )

        dailyPrice2 = DailyPrice(
            DailyPriceId(
                CompanyCode("000000"),
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
        val companyCode = CompanyCode("000000")
        val points = listOf(dailyPrice1, dailyPrice2)
            .map(dailyPriceToPointConverter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonDailyPricesResDto = objectMapper.writeValueAsString(pointResDto)

        given(dailyPriceFindService.findDailyStockValue(companyCode, PagingReqDto()))
            .willReturn(pointResDto)

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

        given(dailyPriceFindService.findDailyStockValue(companyCode, PagingReqDto()))
            .willReturn(pointResDto)

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
            .andExpect(content().string(jsonDailyPricesResponseDto))
            .andDo { print() }
    }
}
