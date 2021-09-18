package com.wubu.api.stockvalue.daily.volume.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.stockvalue.daily.volume.binding.DailyVolumeConverter.DailyVolumeToPointConverter
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolume
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolumeId
import com.wubu.api.stockvalue.daily.volume.repository.DailyVolumeRepository
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

@ExtendWith(MockitoExtension::class)
class DailyVolumeFindServiceTest {

    @Mock
    private lateinit var dailyVolumeRepository: DailyVolumeRepository

    @Spy
    private lateinit var dailyVolumeToPointConverter: DailyVolumeToPointConverter

    @InjectMocks
    private lateinit var dailyVolumeFindService: DailyVolumeFindService

    private lateinit var dailyVolume1: DailyVolume
    private lateinit var dailyVolume2: DailyVolume
    private lateinit var dailyVolume3: DailyVolume
    private lateinit var dailyVolume4: DailyVolume
    private lateinit var dailyVolume5: DailyVolume

    @BeforeEach
    fun setUp() {
        dailyVolume1 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 24)
            ),
            Volume(1)
        )

        dailyVolume2 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 25)
            ),
            Volume(1)
        )

        dailyVolume3 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 26)
            ),
            Volume(1)
        )

        dailyVolume4 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 27)
            ),
            Volume(1)
        )

        dailyVolume5 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                LocalDate.of(1991, 3, 28)
            ),
            Volume(1)
        )
    }

    @Test
    fun `일별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val pagingReqDto = PagingReqDto()
        val reversedDailyVolumes = listOf(dailyVolume4, dailyVolume3, dailyVolume2, dailyVolume1)
        val dailyVolumes = reversedDailyVolumes.reversed()
        val volumes = dailyVolumes.map(dailyVolumeToPointConverter::convert)
            .toList()
        val pointResDto = PointResDto.of(volumes)

        given(dailyVolumeRepository.findAllByIdCompanyCodeOrderByIdDateDesc(companyCode, pagingReqDto.getPageable()))
            .willReturn(reversedDailyVolumes)

        // when
        val foundDailyVolumesResDto = dailyVolumeFindService.findDailyStockValue(companyCode, pagingReqDto)

        // then
        assertThat(foundDailyVolumesResDto).isEqualTo(pointResDto)
    }

    @Test
    fun `default date 주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.now()
        val thisMondayDate = DateUtil.getStartDateOfWeek(date)
        val thisMondayVolume = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                thisMondayDate
            ),
            Volume(1)
        )
        val thisTuesdayVolume = DailyVolume(
            DailyVolumeId(
                CompanyCode("000000"),
                thisMondayDate.plusDays(1)
            ),
            Volume(10)
        )
        val reversedDailyVolumes = listOf(thisTuesdayVolume, thisMondayVolume)
        val dailyVolumes = reversedDailyVolumes.reversed()
        val points = dailyVolumes.map(dailyVolumeToPointConverter::convert)
            .toList()
        val pointResDto = PointResDto.of(points)

        given(
            dailyVolumeRepository.findAllByIdCompanyCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
                companyCode,
                thisMondayDate
            )
        )
            .willReturn(dailyVolumes)

        // when
        val foundDailyVolumesResponseDto = dailyVolumeFindService.findThisWeekStockValue(companyCode)

        // then
        assertThat(foundDailyVolumesResponseDto).isEqualTo(pointResDto)
    }
}
