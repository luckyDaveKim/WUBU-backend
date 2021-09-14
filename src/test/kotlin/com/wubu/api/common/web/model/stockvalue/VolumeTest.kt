package com.wubu.api.common.web.model.stockvalue

import com.wubu.api.common.error.exception.InvalidVolumeException
import com.wubu.api.common.web.model.SingleValue
import com.wubu.api.common.web.model.stockvalue.Volume.VolumeConverter
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VolumeTest {

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
        Assertions.assertThatThrownBy { Volume(volumeValue) }
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
        assertThat(volume1.value).isEqualTo(volumeValue)
        assertThat(volume2.value).isEqualTo(volumeValue)
    }

    @Test
    fun `SingleValue 인터페이스 테스트`() {
        // given
        val volumeValue = 123456L

        // when
        val singleValue: SingleValue<Long> = Volume(volumeValue)

        // then
        assertThat(singleValue.value).isEqualTo(volumeValue)
    }

    @Test
    fun `StockValue 인터페이스 테스트`() {
        // given
        val volumeValue = 123456L

        // when
        val stockValue: StockValue = Volume(volumeValue)

        // then
        assertThat(stockValue).isNotNull
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