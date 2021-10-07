package com.wubu.api.domain.volume.minutely

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class MinutelyVolumeIdTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var date1: LocalDateTime
    private lateinit var date2: LocalDateTime

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        companyCode2 = CompanyCode("000002")
        date1 = LocalDateTime.of(1991, 3, 26, 9, 0)
        date2 = LocalDateTime.of(1991, 3, 27, 9, 0)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val minutelyVolumeId = MinutelyVolumeId(
            companyCode = companyCode1,
            dateTime = date1
        )

        // then
        assertThat(minutelyVolumeId).isNotNull
        assertThat(minutelyVolumeId.companyCode).isEqualTo(companyCode1)
        assertThat(minutelyVolumeId.dateTime).isEqualTo(date1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val minutelyVolumeId1 = MinutelyVolumeId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyVolumeId2 = MinutelyVolumeId(
            companyCode = companyCode1,
            dateTime = date1
        )

        // then
        assertThat(minutelyVolumeId1).isEqualTo(minutelyVolumeId2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val minutelyVolumeId1 = MinutelyVolumeId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyVolumeId2 = MinutelyVolumeId(
            companyCode = companyCode2,
            dateTime = date2
        )

        // then
        assertThat(minutelyVolumeId1).isNotEqualTo(minutelyVolumeId2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val minutelyVolumeId1 = MinutelyVolumeId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyVolumeId2 = MinutelyVolumeId(
            companyCode = companyCode1,
            dateTime = date1
        )

        // then
        assertThat(minutelyVolumeId1.hashCode()).isEqualTo(minutelyVolumeId2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val minutelyVolumeId1 = MinutelyVolumeId(
            companyCode = companyCode1,
            dateTime = date1
        )
        val minutelyVolumeId2 = MinutelyVolumeId(
            companyCode = companyCode2,
            dateTime = date2
        )

        // then
        assertThat(minutelyVolumeId1.hashCode()).isNotEqualTo(minutelyVolumeId2.hashCode())
    }
}
