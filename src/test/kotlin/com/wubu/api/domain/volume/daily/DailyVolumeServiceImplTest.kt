package com.wubu.api.domain.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.infra.volume.daily.DailyVolumeReaderImpl
import com.wubu.api.interfaces.volume.daily.DailyVolumeConverter.DailyVolumeToPointConverter
import com.wubu.api.interfaces.volume.daily.DailyVolumeRes
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
internal class DailyVolumeServiceImplTest {

    @Mock
    private lateinit var dailyVolumeReader: DailyVolumeReaderImpl

    @Spy
    private lateinit var dailyVolumeToPointConverter: DailyVolumeToPointConverter

    @InjectMocks
    private lateinit var dailyVolumeService: DailyVolumeServiceImpl

    private lateinit var dailyVolume1: DailyVolume
    private lateinit var dailyVolume2: DailyVolume

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
        val pagingReqDto = PagingReqDto()
        val dailyVolumes = listOf(dailyVolume1, dailyVolume2)
        val dailyVolumeRes = DailyVolumeRes.of(
            dailyVolumes.map(dailyVolumeToPointConverter::convert)
        )

        given(
            dailyVolumeReader.getDailyVolumes(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(dailyVolumes)

        // when
        val foundDailyVolumeRes = dailyVolumeService.retrieveDailyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundDailyVolumeRes).isEqualTo(dailyVolumeRes)
    }

    @Test
    fun `주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.of(2021, 10, 28)
        val dailyVolumes = listOf(dailyVolume1, dailyVolume2)
        val weekVolumeRes = DailyVolumeRes.of(
            dailyVolumes.map(dailyVolumeToPointConverter::convert)
        )

        given(
            dailyVolumeReader.getThisWeekVolumes(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(dailyVolumes)

        // when
        val foundThisWeekVolumeRes = dailyVolumeService.retrieveThisWeekVolumes(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundThisWeekVolumeRes).isEqualTo(weekVolumeRes)
    }
}
