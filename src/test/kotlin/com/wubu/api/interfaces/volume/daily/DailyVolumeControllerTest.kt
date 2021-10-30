package com.wubu.api.interfaces.volume.daily

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.volume.daily.DailyVolumeFacade
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

@WebMvcTest(DailyVolumeController::class)
internal class DailyVolumeControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var dailyVolumeFacade: DailyVolumeFacade

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
    fun `데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val points = listOf(point1, point2)
        val dailyVolumeRes = DailyVolumeRes.of(points)
        val jsonDailyVolumesResp = objectMapper.writeValueAsString(dailyVolumeRes)

        given(
            dailyVolumeFacade.retrieveDailyVolumes(
                companyCode = companyCode,
                pagingReqDto = PagingReqDto()
            )
        ).willReturn(dailyVolumeRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/daily/volume/companies/{companyCode}", companyCode.value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonDailyVolumesResp))
            .andDo { print() }
    }

    @Test
    fun `페이징 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val points = listOf(point1, point2)
        val dailyVolumeRes = DailyVolumeRes.of(points)
        val jsonDailyVolumesResp = objectMapper.writeValueAsString(dailyVolumeRes)

        given(dailyVolumeFacade.retrieveDailyVolumes(companyCode, PagingReqDto()))
            .willReturn(dailyVolumeRes)

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
            .andExpect(content().string(jsonDailyVolumesResp))
            .andDo { print() }
    }
}
