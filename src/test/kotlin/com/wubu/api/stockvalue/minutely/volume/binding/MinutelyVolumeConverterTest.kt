package com.wubu.api.stockvalue.minutely.volume.binding

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.minutely.volume.binding.MinutelyVolumeConverter.MinutelyVolumeToPointConverter
import com.wubu.api.stockvalue.minutely.volume.entity.MinutelyVolume
import com.wubu.api.stockvalue.minutely.volume.entity.MinutelyVolumeId
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class MinutelyVolumeConverterTest {

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
        Assertions.assertThat(minutelyVolumeConverter).isNotNull
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
        Assertions.assertThat(point.x).isEqualTo(dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        Assertions.assertThat(point.y).isEqualTo(volume)
        Assertions.assertThat(point.z).isEqualTo(0)
        Assertions.assertThat(point.open).isEqualTo(0)
        Assertions.assertThat(point.high).isEqualTo(0)
        Assertions.assertThat(point.low).isEqualTo(0)
        Assertions.assertThat(point.close).isEqualTo(0)
    }
}
