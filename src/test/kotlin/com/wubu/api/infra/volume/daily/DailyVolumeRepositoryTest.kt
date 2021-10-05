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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDate

@SpringBootTest
@ContextConfiguration(initializers = [ConfigDataApplicationContextInitializer::class])
@ActiveProfiles("test")
class DailyVolumeRepositoryTest(
    @Autowired
    private val dailyVolumeRepository: DailyVolumeRepository
) {

    private lateinit var dailyVolume1: DailyVolume
    private lateinit var dailyVolume2: DailyVolume
    private lateinit var dailyVolume3: DailyVolume

    @BeforeEach
    fun setUp() {
        dailyVolume1 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 24)
            ),
            Volume(1)
        )
        dailyVolume2 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 26)
            ),
            Volume(10)
        )
        dailyVolume3 = DailyVolume(
            DailyVolumeId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 27)
            ),
            Volume(100)
        )

        dailyVolumeRepository.deleteAll()

        dailyVolumeRepository.save(dailyVolume1)
        dailyVolumeRepository.save(dailyVolume2)
        dailyVolumeRepository.save(dailyVolume3)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given
        val id = dailyVolume1.id

        // when
        val foundDailVolume = dailyVolumeRepository.findById(id)

        // then
        assertThat(foundDailVolume.get()).isEqualTo(dailyVolume1)
    }

    @Test
    fun `code 기준 리스트 조회 테스트`() {
        // given
        val code = dailyVolume1.id.companyCode
        val pageable = PagingReqDto().getPageable()

        // when
        val foundDailyVolume = dailyVolumeRepository.findAllByIdCompanyCodeOrderByIdDateDesc(code, pageable)

        // then
        assertThat(foundDailyVolume[0]).isEqualTo(dailyVolume3)
        assertThat(foundDailyVolume[1]).isEqualTo(dailyVolume2)
        assertThat(foundDailyVolume[2]).isEqualTo(dailyVolume1)
    }

    @Test
    fun `페이징 테스트`() {
        // given
        val code = dailyVolume1.id.companyCode
        val page = 2
        val pageSize = 1
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailyVolumes = dailyVolumeRepository.findAllByIdCompanyCodeOrderByIdDateDesc(code, pageable)

        // then
        assertThat(foundDailyVolumes.size).isEqualTo(pageSize)
        assertThat(foundDailyVolumes[0]).isEqualTo(dailyVolume2)
    }

    @Test
    fun `페이징 사이즈 테스트`() {
        // given
        val code = dailyVolume1.id.companyCode
        val page = 1
        val pageSize = 2
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailyVolumes = dailyVolumeRepository.findAllByIdCompanyCodeOrderByIdDateDesc(code, pageable)

        // then
        assertThat(foundDailyVolumes.size).isEqualTo(pageSize)
        assertThat(foundDailyVolumes[0]).isEqualTo(dailyVolume3)
        assertThat(foundDailyVolumes[1]).isEqualTo(dailyVolume2)
    }

    @Test
    fun `코드 및 날짜 기준 리스트 조회 테스트`() {
        // given
        val code = dailyVolume2.id.companyCode
        val today = LocalDate.now()
        val startDateOfWeek = DateUtil.getStartDateOfWeek(today)
        val thisWeekDataSize = today.dayOfWeek.value

        // when
        for (dailyVolume in getVolumeBefore6DaysUntilToday()) {
            dailyVolumeRepository.save(dailyVolume)
        }
        val foundThisWeekDailyVolumes =
            dailyVolumeRepository.findAllByIdCompanyCodeAndIdDateGreaterThanEqualOrderByIdDateAsc(
                code,
                startDateOfWeek
            )

        // then
        assertThat(foundThisWeekDailyVolumes.size).isEqualTo(thisWeekDataSize)
    }

    private fun getVolumeBefore6DaysUntilToday(): List<DailyVolume> {
        val volumes = emptyList<DailyVolume>().toMutableList()
        val today = LocalDate.now()

        for (i: Long in -6..0L) {
            val targetDate = today.plusDays(i)
            val dailyVolume = DailyVolume(
                DailyVolumeId(
                    CompanyCode("000001"),
                    targetDate
                ),
                Volume(1)
            )
            volumes.add(dailyVolume)
        }

        return volumes
    }
}
