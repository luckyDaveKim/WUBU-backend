package com.wubu.api.stockvalue.daily.volume.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolume
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolumeId
import com.wubu.api.stockvalue.daily.volume.service.DailyVolumeFindService
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

@WebMvcTest(DailyVolumeController::class)
class DailyVolumeControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    lateinit var dailyVolumeFindService: DailyVolumeFindService

    private val objectMapper = ObjectMapper()
    lateinit var dailyVolume1: DailyVolume
    lateinit var dailyVolume2: DailyVolume

    @BeforeEach
    fun setUp() {
        dailyVolume1 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 26)
            ),
            Volume(1)
        )

        dailyVolume2 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 27)
            ),
            Volume(1)
        )
    }

    @Test
    fun `데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val points = listOf(dailyVolume1, dailyVolume2)
            .map { source ->
                Point(
                    x = source.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                    y = source.volume.value
                )
            }
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonDailyVolumesResponseDto = objectMapper.writeValueAsString(pointResDto)

        given(dailyVolumeFindService.findDailyStockValue(companyCode, PagingReqDto()))
            .willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/volume/companies/{companyCode}", companyCode.value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonDailyVolumesResponseDto))
            .andDo { print() }
    }

    @Test
    fun `페이징 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val points = listOf(dailyVolume1, dailyVolume2)
            .map { source ->
                Point(
                    x = source.id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli(),
                    y = source.volume.value
                )
            }
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonDailyVolumesResponseDto = objectMapper.writeValueAsString(pointResDto)

        given(dailyVolumeFindService.findDailyStockValue(companyCode, PagingReqDto()))
            .willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/volume/companies/{companyCode}", companyCode.value)
                .param("page", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().string(jsonDailyVolumesResponseDto))
            .andDo { print() }
    }
}
