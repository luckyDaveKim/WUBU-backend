package com.wubu.api.stockvalue.daily.volume.binding

import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.daily.volume.binding.DailyVolumeConverter.DailyVolumeToPointConverter
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolume
import com.wubu.api.stockvalue.daily.volume.entity.DailyVolumeId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

class DailyVolumeConverterTest {

    lateinit var dailyVolumeToPointConverter: DailyVolumeToPointConverter

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
                Code("000000"),
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