package com.wubu.api.infra.volume.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.domain.volume.daily.DailyVolume
import com.wubu.api.domain.volume.daily.DailyVolumeId
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
internal class DailyVolumeReaderImplTest {

    @Mock
    private lateinit var dailyVolumeRepository: DailyVolumeRepository

    @InjectMocks
    private lateinit var dailyVolumeReader: DailyVolumeReaderImpl

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
        val reversedDailyVolumes = dailyVolumes.reversed()

        given(
            dailyVolumeRepository.findAllByIdCompanyCodeOrderByIdDateDesc(
                companyCode = companyCode,
                pageable = pagingReqDto.getPageable()
            )
        ).willReturn(reversedDailyVolumes)

        // when
        val foundDailyPrice = dailyVolumeReader.getDailyVolumes(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundDailyPrice).isEqualTo(dailyVolumes)
    }

    @Test
    fun `주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.of(2021, 10, 28)
        val startDateOfThisWeek = DateUtil.getStartDateOfWeek(date)
        val startDateOfNextWeek = DateUtil.getStartDateOfNextWeek(date)
        val dailyVolumes = listOf(dailyVolume1, dailyVolume2)

        given(
            dailyVolumeRepository.findAllById_CompanyCodeAndId_DateGreaterThanEqualAndId_DateLessThanOrderByIdDateAsc(
                companyCode = companyCode,
                greaterThanEqualDate = startDateOfThisWeek,
                lessThanDate = startDateOfNextWeek
            )
        ).willReturn(dailyVolumes)

        // when
        val foundThisWeekPrice = dailyVolumeReader.getThisWeekVolumes(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundThisWeekPrice).isEqualTo(dailyVolumes)
    }
}
