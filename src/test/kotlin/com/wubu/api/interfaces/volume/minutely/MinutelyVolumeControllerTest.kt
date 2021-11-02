package com.wubu.api.interfaces.volume.minutely

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.volume.minutely.MinutelyVolumeFacade
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
import java.time.LocalDate

@WebMvcTest(MinutelyVolumeController::class)
internal class MinutelyVolumeControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var minutelyVolumeFacade: MinutelyVolumeFacade

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
    fun `특정 회사 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val pagingReqDto = PagingReqDto(
            page = 1,
            pageSize = 2
        )

        val points = listOf(point1, point2)
        val minutelyVolumeRes = MinutelyVolumeRes.of(points)
        val jsonMinutelyVolumeRes = objectMapper.writeValueAsString(minutelyVolumeRes)

        given(
            minutelyVolumeFacade.retrieveMinutelyVolumes(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(minutelyVolumeRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/volume/companies/{companyCode}", companyCode.value)
                .param("page", "1")
                .param("pageSize", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonMinutelyVolumeRes))
            .andDo { print() }
    }

    @Test
    fun `default 페이징 정보 기반 특정 회사 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val points = listOf(point1, point2)
        val minutelyVolumeRes = MinutelyVolumeRes.of(points)
        val jsonMinutelyVolumeRes = objectMapper.writeValueAsString(minutelyVolumeRes)

        given(
            minutelyVolumeFacade.retrieveMinutelyVolumes(
                companyCode = companyCode,
                pagingReqDto = PagingReqDto()
            )
        ).willReturn(minutelyVolumeRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/volume/companies/{companyCode}", companyCode.value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonMinutelyVolumeRes))
            .andDo { print() }
    }

    @Test
    fun `특정 회사 및 특정일 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(point1, point2)
        val minutelyVolumeRes = MinutelyVolumeRes.of(points)
        val jsonMinutelyVolumeRes = objectMapper.writeValueAsString(minutelyVolumeRes)

        given(
            minutelyVolumeFacade.retrieveMinutelyVolumesAtDate(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(minutelyVolumeRes)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/volume/{date}/companies/{companyCode}", date, companyCode.value)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonMinutelyVolumeRes))
            .andDo { print() }
    }
}
