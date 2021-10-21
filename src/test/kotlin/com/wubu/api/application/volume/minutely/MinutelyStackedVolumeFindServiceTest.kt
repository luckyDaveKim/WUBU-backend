package com.wubu.api.application.volume.minutely

import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.volume.minutely.MinutelyVolume
import com.wubu.api.domain.volume.minutely.MinutelyVolumeId
import com.wubu.api.infra.volume.minutely.MinutelyVolumeRepository
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeConverter
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
internal class MinutelyStackedVolumeFindServiceTest {

    @Mock
    private lateinit var minutelyVolumeRepository: MinutelyVolumeRepository

    @Spy
    private lateinit var converter: MinutelyVolumeConverter.MinutelyVolumeToPointConverter

    @InjectMocks
    private lateinit var minutelyStackedVolumeFindService: MinutelyStackedVolumeFindService

    private lateinit var minutelyVolume1: MinutelyVolume
    private lateinit var minutelyVolume2: MinutelyVolume
    private lateinit var minutelyVolume3: MinutelyVolume

    @BeforeEach
    fun setUp() {
        minutelyVolume1 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = CompanyCode("000001"),
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0)
            ),
            volume = Volume(1)
        )
        minutelyVolume2 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = CompanyCode("000001"),
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 1)
            ),
            volume = Volume(2)
        )
        minutelyVolume3 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = CompanyCode("000001"),
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 2)
            ),
            volume = Volume(3)
        )
    }

    @Test
    fun `특정일 분별 누적 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()
        val minutelyVolumes = listOf(minutelyVolume1, minutelyVolume2, minutelyVolume3)
        val reversedMinutelyVolumes = minutelyVolumes.reversed()
        val stackedPointResDto = PointResDto.of(
            minutelyVolumes.stream()
                .collect(MinutelyVolume.toStacked())
                .map(converter::convert)
        )

        given(
            minutelyVolumeRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
        ).willReturn(reversedMinutelyVolumes)

        // when
        val foundPointResDto = minutelyStackedVolumeFindService.findMinutelyStackedStockValueAtDate(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundPointResDto).isNotNull
        assertThat(foundPointResDto).isEqualTo(stackedPointResDto)
    }
}
