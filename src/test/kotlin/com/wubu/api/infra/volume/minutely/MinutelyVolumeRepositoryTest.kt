package com.wubu.api.infra.volume.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.volume.minutely.MinutelyVolume
import com.wubu.api.domain.volume.minutely.MinutelyVolumeId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@ContextConfiguration(initializers = [ConfigDataApplicationContextInitializer::class])
@ActiveProfiles("test")
class MinutelyVolumeRepositoryTest(
    @Autowired
    private val minutelyVolumeRepository: MinutelyVolumeRepository
) {

    private lateinit var minutelyVolume1: MinutelyVolume
    private lateinit var minutelyVolume2: MinutelyVolume
    private lateinit var minutelyVolume3: MinutelyVolume

    @BeforeEach
    fun setUp() {
        minutelyVolumeRepository.deleteAll()

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

        minutelyVolumeRepository.save(minutelyVolume1)
        minutelyVolumeRepository.save(minutelyVolume2)
        minutelyVolumeRepository.save(minutelyVolume3)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given

        // when
        val foundMinutelyVolume = minutelyVolumeRepository.findById(minutelyVolume1.id)

        // then
        assertThat(foundMinutelyVolume.get()).isEqualTo(minutelyVolume1)
    }

    @Test
    fun `코드 기준 리스트 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val page = 1
        val pageSize = 2
        val pageable = PagingReqDto(
            page = page,
            pageSize = pageSize
        ).getPageable()

        // when
        val foundMinutelyVolumes = minutelyVolumeRepository.findAllById_CompanyCodeOrderById_DateTimeDesc(
            companyCode = companyCode,
            pageable = pageable
        )

        // then
        assertThat(foundMinutelyVolumes).isNotNull
        assertThat(foundMinutelyVolumes.size).isEqualTo(pageSize)
        assertThat(foundMinutelyVolumes[0]).isEqualTo(minutelyVolume3)
        assertThat(foundMinutelyVolumes[1]).isEqualTo(minutelyVolume2)
    }

    @Test
    fun `코드 및 날짜 기준 리스트 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val today = LocalDate.now()
        val afterEqualDateTime = today.atStartOfDay()
        val beforeDateTime = today.plusDays(1).atStartOfDay()

        for (minutelyVolume in getVolumesYesterdayToTomorrow()) {
            minutelyVolumeRepository.save(minutelyVolume)
        }

        // when
        val foundMinutelyVolumes =
            minutelyVolumeRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )

        // then
        assertThat(foundMinutelyVolumes).isNotNull
        assertThat(foundMinutelyVolumes.size).isEqualTo(1)
    }

    private fun getVolumesYesterdayToTomorrow(): List<MinutelyVolume> {
        val volume = emptyList<MinutelyVolume>().toMutableList()
        val today = LocalDateTime.now()

        for (i: Long in -1..1L) {
            val targetDateTime = today.plusDays(i)
            val minutelyVolume = MinutelyVolume(
                id = MinutelyVolumeId(companyCode = CompanyCode("000001"), dateTime = targetDateTime),
                volume = Volume(1)
            )
            volume.add(minutelyVolume)
        }

        return volume
    }
}
