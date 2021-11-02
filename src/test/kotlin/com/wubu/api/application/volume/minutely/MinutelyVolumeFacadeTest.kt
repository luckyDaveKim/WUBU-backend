package com.wubu.api.application.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.domain.volume.minutely.MinutelyVolumeServiceImpl
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeRes
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
internal class MinutelyVolumeFacadeTest {

    @Mock
    private lateinit var minutelyVolumeService: MinutelyVolumeServiceImpl

    @InjectMocks
    private lateinit var minutelyVolumeFacade: MinutelyVolumeFacade

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
    fun `분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val pagingReqDto = PagingReqDto(
            page = 1,
            pageSize = 2
        )
        val points = listOf(point1, point2)
        val minutelyVolumeRes = MinutelyVolumeRes.of(points)

        given(
            minutelyVolumeService.retrieveMinutelyVolumes(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(minutelyVolumeRes)

        // when
        val foundMinutelyVolumeRes = minutelyVolumeFacade.retrieveMinutelyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundMinutelyVolumeRes).isNotNull
        assertThat(foundMinutelyVolumeRes).isEqualTo(minutelyVolumeRes)
    }

    @Test
    fun `특정일 분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val points = listOf(point1, point2)
        val minutelyVolumeRes = MinutelyVolumeRes.of(points)

        given(
            minutelyVolumeService.retrieveMinutelyVolumesAtDate(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(minutelyVolumeRes)

        // when
        val foundMinutelyVolumeRes = minutelyVolumeFacade.retrieveMinutelyVolumesAtDate(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundMinutelyVolumeRes).isNotNull
        assertThat(foundMinutelyVolumeRes).isEqualTo(foundMinutelyVolumeRes)
    }
}
