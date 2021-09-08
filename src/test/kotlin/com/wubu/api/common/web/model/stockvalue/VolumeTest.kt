package com.wubu.api.common.web.model.stockvalue

import com.wubu.api.common.error.exception.InvalidVolumeException
import com.wubu.api.common.web.model.stockvalue.Volume.VolumeConverter
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VolumeTest {

    @Test
    fun `생성 테스트`() {
        val volumeValue = 123456L
        val volume = Price(volumeValue)

        assertThat(volume.value).isEqualTo(volumeValue)
    }

    @Test
    fun `거래량이 음수인 오류 테스트`() {
        Assertions.assertThatThrownBy { Volume(-1) }
                .isInstanceOf(InvalidVolumeException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        val volumeValue = 1L
        val volume1 = Price(volumeValue)
        val volume2 = Price(volumeValue)

        assertThat(volume1).isEqualTo(volume2)
        assertThat(volume1.value).isEqualTo(volumeValue)
        assertThat(volume2.value).isEqualTo(volumeValue)
    }

    @Test
    fun `DB to Object 테스트`() {
        val volumeValue = 100L
        val volume = VolumeConverter().convertToEntityAttribute(volumeValue)

        assertThat(volume).isNotNull
        assertThat(volume.value).isEqualTo(volumeValue)
    }

    @Test
    fun `DB to Object 0 값 테스트`() {
        val volumeValue = 0L
        val volume = VolumeConverter().convertToEntityAttribute(volumeValue)

        assertThat(volume).isNotNull
        assertThat(volume.value).isEqualTo(volumeValue)
    }

    @Test
    fun `DB to Object Null 값 테스트`() {
        val volumeValue = null
        val volume = VolumeConverter().convertToEntityAttribute(volumeValue)

        assertThat(volume).isNotNull
        assertThat(volume.value).isEqualTo(0)
    }

    @Test
    fun `Object to DB 테스트`() {
        val volumeValue = 100L
        val volume = Volume(volumeValue)
        val dbVolume = VolumeConverter().convertToDatabaseColumn(volume)

        assertThat(dbVolume).isEqualTo(volumeValue)
    }

    @Test
    fun `Object to DB 0 값 테스트`() {
        val volumeValue = 0L
        val volume = Volume(volumeValue)
        val dbVolume = VolumeConverter().convertToDatabaseColumn(volume)

        assertThat(dbVolume).isEqualTo(volumeValue)
    }

    @Test
    fun `Object to DB Null 값 테스트`() {
        val volume = null
        val dbVolume = VolumeConverter().convertToDatabaseColumn(volume)

        assertThat(dbVolume).isEqualTo(0)
    }

}