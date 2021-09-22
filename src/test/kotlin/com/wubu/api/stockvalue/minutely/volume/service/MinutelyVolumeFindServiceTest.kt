package com.wubu.api.stockvalue.minutely.volume.service

import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.minutely.volume.binding.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import com.wubu.api.stockvalue.minutely.volume.entity.MinutelyVolume
import com.wubu.api.stockvalue.minutely.volume.entity.MinutelyVolumeId
import com.wubu.api.stockvalue.minutely.volume.repository.MinutelyVolumeRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class MinutelyVolumeFindServiceTest {

    @Mock
    private lateinit var minutelyVolumeRepository: MinutelyVolumeRepository

    @Spy
    private lateinit var converter: MinutelyVolumeToPointConverter

    @InjectMocks
    private lateinit var minutelyVolumeFindService: MinutelyVolumeFindService

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
    fun `특정일 분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()
        val minutelyVolumes = listOf(minutelyVolume1, minutelyVolume2, minutelyVolume3)
        val reversedMinutelyVolumes = minutelyVolumes.reversed()
        val points = minutelyVolumes.map(converter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)

        BDDMockito.given(
            minutelyVolumeRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
        ).willReturn(reversedMinutelyVolumes)

        // when
        val foundPointResDto = minutelyVolumeFindService.findMinutelyStockValueAtDate(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundPointResDto).isNotNull
        assertThat(foundPointResDto).isEqualTo(pointResDto)
    }
}
