package com.wubu.api.common.web.dto

import com.wubu.api.common.web.model.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PointDtoTest {

    private lateinit var point1: Point
    private lateinit var point2: Point

    @BeforeEach
    fun setUp() {
        point1 = Point(
            x = 1,
            y = 2,
            z = 3,
            open = 4,
            high = 5,
            low = 6,
            close = 7
        )
        point2 = Point(
            x = 10,
            y = 20,
            z = 30,
            open = 40,
            high = 50,
            low = 60,
            close = 70
        )
    }

    @Test
    fun `int 생성 테스트`() {
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
    fun `long 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto(
            x = 1L,
            y = 2L,
            z = 3L,
            open = 4L,
            high = 5L,
            low = 6L,
            close = 7L
        )

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1L)
        assertThat(pointDto.y).isEqualTo(2L)
        assertThat(pointDto.z).isEqualTo(3L)
        assertThat(pointDto.open).isEqualTo(4L)
        assertThat(pointDto.high).isEqualTo(5L)
        assertThat(pointDto.low).isEqualTo(6L)
        assertThat(pointDto.close).isEqualTo(7L)
    }

    @Test
    fun `float 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto(
            x = 1F,
            y = 2F,
            z = 3F,
            open = 4F,
            high = 5F,
            low = 6F,
            close = 7F
        )

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1F)
        assertThat(pointDto.y).isEqualTo(2F)
        assertThat(pointDto.z).isEqualTo(3F)
        assertThat(pointDto.open).isEqualTo(4F)
        assertThat(pointDto.high).isEqualTo(5F)
        assertThat(pointDto.low).isEqualTo(6F)
        assertThat(pointDto.close).isEqualTo(7F)
    }

    @Test
    fun `double 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto(
            x = 1.1,
            y = 2.1,
            z = 3.1,
            open = 4.1,
            high = 5.1,
            low = 6.1,
            close = 7.1
        )

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1.1)
        assertThat(pointDto.y).isEqualTo(2.1)
        assertThat(pointDto.z).isEqualTo(3.1)
        assertThat(pointDto.open).isEqualTo(4.1)
        assertThat(pointDto.high).isEqualTo(5.1)
        assertThat(pointDto.low).isEqualTo(6.1)
        assertThat(pointDto.close).isEqualTo(7.1)
    }

    @Test
    fun `of int 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto.of(
            Point(
                x = 1,
                y = 2,
                z = 3,
                open = 4,
                high = 5,
                low = 6,
                close = 7
            )
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
    fun `of long 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto.of(
            Point(
                x = 1L,
                y = 2L,
                z = 3L,
                open = 4L,
                high = 5L,
                low = 6L,
                close = 7L
            )
        )

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1L)
        assertThat(pointDto.y).isEqualTo(2L)
        assertThat(pointDto.z).isEqualTo(3L)
        assertThat(pointDto.open).isEqualTo(4L)
        assertThat(pointDto.high).isEqualTo(5L)
        assertThat(pointDto.low).isEqualTo(6L)
        assertThat(pointDto.close).isEqualTo(7L)
    }

    @Test
    fun `of float 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto.of(
            Point(
                x = 1F,
                y = 2F,
                z = 3F,
                open = 4F,
                high = 5F,
                low = 6F,
                close = 7F
            )
        )

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1F)
        assertThat(pointDto.y).isEqualTo(2F)
        assertThat(pointDto.z).isEqualTo(3F)
        assertThat(pointDto.open).isEqualTo(4F)
        assertThat(pointDto.high).isEqualTo(5F)
        assertThat(pointDto.low).isEqualTo(6F)
        assertThat(pointDto.close).isEqualTo(7F)
    }

    @Test
    fun `of double 생성 테스트`() {
        // given

        // when
        val pointDto = PointDto.of(
            Point(
                x = 1.1,
                y = 2.1,
                z = 3.1,
                open = 4.1,
                high = 5.1,
                low = 6.1,
                close = 7.1
            )
        )

        // then
        assertThat(pointDto).isNotNull
        assertThat(pointDto.x).isEqualTo(1.1)
        assertThat(pointDto.y).isEqualTo(2.1)
        assertThat(pointDto.z).isEqualTo(3.1)
        assertThat(pointDto.open).isEqualTo(4.1)
        assertThat(pointDto.high).isEqualTo(5.1)
        assertThat(pointDto.low).isEqualTo(6.1)
        assertThat(pointDto.close).isEqualTo(7.1)
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
