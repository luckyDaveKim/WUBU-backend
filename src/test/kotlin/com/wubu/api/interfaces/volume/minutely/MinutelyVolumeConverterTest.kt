package com.wubu.api.interfaces.volume.minutely

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.volume.minutely.MinutelyVolume
import com.wubu.api.domain.volume.minutely.MinutelyVolumeId
import com.wubu.api.interfaces.volume.minutely.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class MinutelyVolumeConverterTest {

    private lateinit var converter: MinutelyVolumeToPointConverter

    @BeforeEach
    fun setUp() {
        converter = MinutelyVolumeToPointConverter()
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyVolumeConverter = MinutelyVolumeConverter()

        // then
        assertThat(minutelyVolumeConverter).isNotNull
    }

    @Test
    fun `MinutelyVolume to Point 테스트`() {
        // given
        val dateTime = LocalDateTime.of(1991, 3, 26, 9, 0)
        val volume = 1L
        val minutelyVolumeId = MinutelyVolumeId(
            companyCode = CompanyCode("000001"),
            dateTime = dateTime
        )
        val minutelyVolume = MinutelyVolume(
            id = minutelyVolumeId,
            volume = Volume(volume)
        )

        // when
        val point = converter.convert(minutelyVolume)

        // then
        assertThat(point.x).isEqualTo(dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(point.y).isEqualTo(volume)
        assertThat(point.z).isEqualTo(0)
        assertThat(point.open).isEqualTo(0)
        assertThat(point.high).isEqualTo(0)
        assertThat(point.low).isEqualTo(0)
        assertThat(point.close).isEqualTo(0)
    }
}
