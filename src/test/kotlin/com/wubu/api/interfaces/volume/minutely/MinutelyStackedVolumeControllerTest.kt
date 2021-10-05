package com.wubu.api.interfaces.volume.minutely

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.volume.minutely.MinutelyStackedVolumeFindService
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.volume.minutely.MinutelyVolume
import com.wubu.api.domain.volume.minutely.MinutelyVolumeId
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
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

@WebMvcTest(MinutelyStackedVolumeController::class)
class MinutelyStackedVolumeControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var minutelyStackedVolumeFindService: MinutelyStackedVolumeFindService

    private val converter = MinutelyVolumeToPointConverter()
    private val objectMapper = ObjectMapper()
    private lateinit var companyCode: CompanyCode
    private lateinit var minutelyVolume1: MinutelyVolume
    private lateinit var minutelyVolume2: MinutelyVolume

    @BeforeEach
    fun setUp() {
        companyCode = CompanyCode("000001")
        minutelyVolume1 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode,
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0)
            ),
            volume = Volume(1)
        )
        minutelyVolume2 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode,
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 1)
            ),
            volume = Volume(11)
        )
    }

    @Test
    fun `특정일 누적 데이터 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(minutelyVolume1, minutelyVolume2)
            .map(converter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)
        val jsonMinutelyVolumeResDto = objectMapper.writeValueAsString(pointResDto)

        given(
            minutelyStackedVolumeFindService.findMinutelyStackedStockValueAtDate(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(pointResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/minutely/stacked/volume/companies/{companyCode}", companyCode.value)
                .param("date", date.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonMinutelyVolumeResDto))
            .andDo { print() }
    }
}
