package com.wubu.api.common.web.dto

import com.wubu.api.common.web.model.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PointDtoTest {

    lateinit var point1: Point
    lateinit var point2: Point

    @BeforeEach
    fun setUp() {
        point1 = Point(
                x = 1,
                y = 2,
                z = 3,
                open = 4,
                high = 5,
                low = 6,
                close = 7)
        point2 = Point(
                x = 10,
                y = 20,
                z = 30,
                open = 40,
                high = 50,
                low = 60,
                close = 70)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val pointDto = PointDto(
                x = 1,
                y = 2,
                z = 3,
                open = 4,
                high = 5,
                low = 6,
                close = 7
        )

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1)
        assertThat(pointDto.y).isEqualTo(2)
        assertThat(pointDto.z).isEqualTo(3)
        assertThat(pointDto.open).isEqualTo(4)
        assertThat(pointDto.high).isEqualTo(5)
        assertThat(pointDto.low).isEqualTo(6)
        assertThat(pointDto.close).isEqualTo(7)
    }

    @Test
    fun `of 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto.of(point1)

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1)
        assertThat(pointDto.y).isEqualTo(2)
        assertThat(pointDto.z).isEqualTo(3)
        assertThat(pointDto.open).isEqualTo(4)
        assertThat(pointDto.high).isEqualTo(5)
        assertThat(pointDto.low).isEqualTo(6)
        assertThat(pointDto.close).isEqualTo(7)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val pointDto1 = PointDto.of(point1)
        val pointDto2 = PointDto.of(point1)

        // then
        assertThat(pointDto1).isEqualTo(pointDto2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val pointDto1 = PointDto.of(point1)
        val pointDto2 = PointDto.of(point2)

        // then
        assertThat(pointDto1).isNotEqualTo(pointDto2)
    }

}