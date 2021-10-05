package com.wubu.api.interfaces.volume.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.volume.daily.DailyVolume
import com.wubu.api.domain.volume.daily.DailyVolumeId
import com.wubu.api.interfaces.volume.daily.DailyVolumeConverter.DailyVolumeToPointConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

class DailyVolumeConverterTest {

    private lateinit var dailyVolumeToPointConverter: DailyVolumeToPointConverter

    @BeforeEach
    fun setUp() {
        dailyVolumeToPointConverter = DailyVolumeToPointConverter()
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyVolumeConverter = DailyVolumeConverter()

        // then
        assertThat(dailyVolumeConverter).isNotNull
    }

    @Test
    fun `DailyVolume to Point 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val volume = 1L
        val dailyVolumeId = DailyVolumeId(
            CompanyCode("000000"),
            date
        )
        val dailyVolume = DailyVolume(
            dailyVolumeId,
            Volume(volume)
        )

        // when
        val point = dailyVolumeToPointConverter.convert(dailyVolume)

        // then
        assertThat(point.x).isEqualTo(date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(point.y).isEqualTo(volume)
    }
}
