package com.wubu.api.application.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.volume.daily.DailyVolumeServiceImpl
import com.wubu.api.interfaces.volume.daily.DailyVolumeRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class DailyVolumeFacadeTest {

    @Mock
    private lateinit var dailyVolumeService: DailyVolumeServiceImpl

    @InjectMocks
    private lateinit var dailyVolumeFacade: DailyVolumeFacade

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
    fun `일별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val pagingReqDto = PagingReqDto()
        val points = listOf(point1, point2)
        val dailyVolumeRes = DailyVolumeRes.of(points)

        given(
            dailyVolumeService.retrieveDailyVolumes(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(dailyVolumeRes)

        // when
        val foundDailyVolumesResDto = dailyVolumeFacade.retrieveDailyVolumes(companyCode, pagingReqDto)

        // then
        assertThat(foundDailyVolumesResDto).isEqualTo(dailyVolumeRes)
    }

    @Test
    fun `주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.of(2021, 10, 28)
        val points = listOf(point1, point2)
        val weekVolumeRes = DailyVolumeRes.of(points)

        given(
            dailyVolumeService.retrieveThisWeekVolumes(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(weekVolumeRes)

        // when
        val foundThisWeekPriceRes = dailyVolumeFacade.retrieveThisWeekVolumes(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundThisWeekPriceRes).isEqualTo(weekVolumeRes)
    }

    @Test
    fun `default date 주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.now()
        val points = listOf(point1, point2)
        val dailyVolumeRes = DailyVolumeRes.of(points)

        given(
            dailyVolumeService.retrieveThisWeekVolumes(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(dailyVolumeRes)

        // when
        val foundDailyVolumesResponseDto = dailyVolumeFacade.retrieveThisWeekVolumes(companyCode)

        // then
        assertThat(foundDailyVolumesResponseDto).isEqualTo(dailyVolumeRes)
    }
}
