package com.wubu.api.domain.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class MinutelyVolumeServiceImplTest {

    @Mock
    private lateinit var minutelyVolumeReader: MinutelyVolumeReader

    @Spy
    private lateinit var minutelyVolumeToPointConverter: MinutelyVolumeToPointConverter

    @InjectMocks
    private lateinit var minutelyVolumeService: MinutelyVolumeServiceImpl

    private lateinit var companyCode: CompanyCode
    private lateinit var minutelyVolume1: MinutelyVolume
    private lateinit var minutelyVolume2: MinutelyVolume

    @BeforeEach
    fun setUp() {
        companyCode = CompanyCode("000000")
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
            volume = Volume(10)
        )
    }

    @Test
    fun `분별 데이터 조회 테스트`() {
        // given
        val pagingReqDto = PagingReqDto(
            page = 1,
            pageSize = 2
        )
        val minutelyVolumes = listOf(minutelyVolume1, minutelyVolume2)
        val minutelyVolumeRes = MinutelyVolumeRes.of(
            minutelyVolumes.map(minutelyVolumeToPointConverter::convert)
        )

        given(
            minutelyVolumeReader.getMinutelyVolumes(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(minutelyVolumes)

        // when
        val foundPointRes = minutelyVolumeService.retrieveMinutelyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundPointRes).isNotNull
        assertThat(foundPointRes).isEqualTo(minutelyVolumeRes)
    }

    @Test
    fun `특정일 분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val minutelyVolumes = listOf(minutelyVolume1, minutelyVolume2)
        val minutelyVolumeRes = MinutelyVolumeRes.of(
            minutelyVolumes.map(minutelyVolumeToPointConverter::convert)
        )

        given(
            minutelyVolumeReader.getMinutelyVolumesAtDate(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(minutelyVolumes)

        // when
        val foundPointRes = minutelyVolumeService.retrieveMinutelyVolumesAtDate(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundPointRes).isNotNull
        assertThat(foundPointRes).isEqualTo(minutelyVolumeRes)
    }
}
