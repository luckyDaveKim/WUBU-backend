package com.wubu.api.stockvalue.daily.volume.entity

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DailyVolumeTest {

    lateinit var companyCode1: CompanyCode
    lateinit var companyCode2: CompanyCode
    lateinit var date1: LocalDate
    lateinit var date2: LocalDate
    lateinit var volume1: Volume
    lateinit var volume2: Volume

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        date1 = LocalDate.of(1991, 3, 26)
        date2 = LocalDate.of(1991, 3, 27)
        volume1 = Volume(1)
        volume2 = Volume(10)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyVolume = DailyVolume(
                DailyVolumeId(companyCode1, date1),
                volume1)

        // then
        assertThat(dailyVolume).isNotNull
        assertThat(dailyVolume.id).isNotNull
        assertThat(dailyVolume.id.companyCode).isEqualTo(companyCode1)
        assertThat(dailyVolume.id.date).isEqualTo(date1)
        assertThat(dailyVolume.volume).isEqualTo(volume1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyVolume1 = DailyVolume(
                DailyVolumeId(companyCode1, date1),
                volume1)
        val dailyVolume2 = DailyVolume(
                DailyVolumeId(companyCode1, date1),
                volume1)

        // then
        assertThat(dailyVolume1).isEqualTo(dailyVolume2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val dailyVolume1 = DailyVolume(
                DailyVolumeId(companyCode1, date1),
                volume1)
        val dailyVolume2 = DailyVolume(
                DailyVolumeId(companyCode2, date2),
                volume2)

        // then
        assertThat(dailyVolume1).isNotEqualTo(dailyVolume2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val dailyVolume1 = DailyVolume(
                DailyVolumeId(companyCode1, date1),
                volume1)
        val dailyVolume2 = DailyVolume(
                DailyVolumeId(companyCode1, date1),
                volume1)

        // then
        assertThat(dailyVolume1.hashCode()).isEqualTo(dailyVolume2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val dailyVolume1 = DailyVolume(
                DailyVolumeId(companyCode1, date1),
                volume1)
        val dailyVolume2 = DailyVolume(
                DailyVolumeId(companyCode2, date2),
                volume2)

        // then
        assertThat(dailyVolume1.hashCode()).isNotEqualTo(dailyVolume2.hashCode())
    }

}