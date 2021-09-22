package com.wubu.api.stockvalue.minutely.volume.entity

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MinutelyVolumeTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var dateTime1: LocalDateTime
    private lateinit var dateTime2: LocalDateTime
    private lateinit var volume1: Volume
    private lateinit var volume2: Volume

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        dateTime1 = LocalDateTime.of(1991, 3, 26, 9, 0)
        dateTime2 = LocalDateTime.of(1991, 3, 27, 9, 0)
        volume1 = Volume(1)
        volume2 = Volume(10)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyVolume = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode1,
                dateTime = dateTime1
            ),
            volume = volume1
        )

        // then
        assertThat(minutelyVolume).isNotNull
        assertThat(minutelyVolume.id).isNotNull
        assertThat(minutelyVolume.id.companyCode).isEqualTo(companyCode1)
        assertThat(minutelyVolume.id.dateTime).isEqualTo(dateTime1)
        assertThat(minutelyVolume.volume).isEqualTo(volume1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val minutelyVolume1 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode1,
                dateTime = dateTime1
            ),
            volume = volume1
        )
        val minutelyVolume2 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode1,
                dateTime = dateTime1
            ),
            volume = volume1
        )

        // then
        assertThat(minutelyVolume1).isEqualTo(minutelyVolume2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val minutelyVolume1 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode1,
                dateTime = dateTime1
            ),
            volume = volume1
        )
        val minutelyVolume2 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode2,
                dateTime = dateTime2
            ),
            volume = volume2
        )

        // then
        assertThat(minutelyVolume1).isNotEqualTo(minutelyVolume2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val minutelyVolume1 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode1,
                dateTime = dateTime1
            ),
            volume = volume1
        )
        val minutelyVolume2 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode1,
                dateTime = dateTime1
            ),
            volume = volume1
        )

        // then
        assertThat(minutelyVolume1.hashCode()).isEqualTo(minutelyVolume2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val minutelyVolume1 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode1,
                dateTime = dateTime1
            ),
            volume = volume1
        )
        val minutelyVolume2 = MinutelyVolume(
            id = MinutelyVolumeId(
                companyCode = companyCode2,
                dateTime = dateTime2
            ),
            volume = volume2
        )

        // then
        assertThat(minutelyVolume1.hashCode()).isNotEqualTo(minutelyVolume2.hashCode())
    }
}
