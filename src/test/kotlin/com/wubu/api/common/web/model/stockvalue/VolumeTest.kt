package com.wubu.api.common.web.model.stockvalue

import com.wubu.api.common.exception.InvalidVolumeException
import com.wubu.api.common.web.model.SingleValue
import com.wubu.api.common.web.model.stockvalue.Volume.VolumeConverter
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class VolumeTest {

    @Test
    fun `생성 테스트`() {
        // given
        val volumeValue = 123456L

        // when
        val volume = Volume(volumeValue)

        // then
        assertThat(volume.value).isEqualTo(volumeValue)
    }

    @Test
    fun `거래량이 음수인 오류 테스트`() {
        // given
        val volumeValue = -1L

        // when

        // then
        assertThatThrownBy { Volume(volumeValue) }
            .isInstanceOf(InvalidVolumeException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val volumeValue = 1L

        // when
        val volume1 = Volume(volumeValue)
        val volume2 = Volume(volumeValue)

        // then
        assertThat(volume1).isEqualTo(volume2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given
        val volumeValue1 = 1L
        val volumeValue2 = 2L

        // when
        val volume1 = Volume(volumeValue1)
        val volume2 = Volume(volumeValue2)

        // then
        assertThat(volume1).isNotEqualTo(volume2)
    }

    @Test
    fun `SingleValue 인터페이스 테스트`() {
        // given
        val volumeValue = 123456L

        // when
        val volume = Volume(volumeValue)

        // then
        assertThat(volume).isInstanceOf(SingleValue::class.java)
    }

    @Test
    fun `StockValue 인터페이스 테스트`() {
        // given
        val volumeValue = 123456L

        // when
        val volume = Volume(volumeValue)

        // then
        assertThat(volume).isNotNull
        assertThat(volume).isInstanceOf(StockValue::class.java)
        assertThat(volume.value).isEqualTo(volumeValue)
    }

    @Test
    fun `DB to Object 테스트`() {
        // given
        val volumeValue = 100L

        // when
        val volume = VolumeConverter().convertToEntityAttribute(volumeValue)

        // then
        assertThat(volume.value).isEqualTo(volumeValue)
    }

    @Test
    fun `DB to Object 0 값 테스트`() {
        // given
        val volumeValue = 0L

        // when
        val volume = VolumeConverter().convertToEntityAttribute(volumeValue)

        // then
        assertThat(volume.value).isEqualTo(volumeValue)
    }

    @Test
    fun `DB to Object Null 값 테스트`() {
        // given
        val volumeValue = null

        // when
        val volume = VolumeConverter().convertToEntityAttribute(volumeValue)

        // then
        assertThat(volume.value).isEqualTo(0)
    }

    @Test
    fun `Object to DB 테스트`() {
        // given
        val volumeValue = 100L
        val volume = Volume(volumeValue)

        // when
        val dbVolume = VolumeConverter().convertToDatabaseColumn(volume)

        // then
        assertThat(dbVolume).isEqualTo(volumeValue)
    }

    @Test
    fun `Object to DB 0 값 테스트`() {
        // given
        val volumeValue = 0L
        val volume = Volume(volumeValue)

        // when
        val dbVolume = VolumeConverter().convertToDatabaseColumn(volume)

        // then
        assertThat(dbVolume).isEqualTo(volumeValue)
    }

    @Test
    fun `Object to DB Null 값 테스트`() {
        // given
        val volume = null

        // when
        val dbVolume = VolumeConverter().convertToDatabaseColumn(volume)

        // then
        assertThat(dbVolume).isEqualTo(0)
    }
}
