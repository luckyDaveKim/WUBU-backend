package com.wubu.api.common.web.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PointTest {

    private lateinit var x1: Number
    private lateinit var y1: Number
    private lateinit var z1: Number
    private lateinit var open1: Number
    private lateinit var high1: Number
    private lateinit var low1: Number
    private lateinit var close1: Number
    private lateinit var x2: Number
    private lateinit var y2: Number
    private lateinit var z2: Number
    private lateinit var open2: Number
    private lateinit var high2: Number
    private lateinit var low2: Number
    private lateinit var close2: Number

    @BeforeEach
    fun setUp() {
        x1 = 1
        y1 = 2
        z1 = 3
        open1 = 4
        high1 = 5
        low1 = 6
        close1 = 7

        x2 = 10
        y2 = 20
        z2 = 30
        open2 = 40
        high2 = 50
        low2 = 60
        close2 = 70
    }

    @Test
    fun `int 생성 테스트`() {
        // given
        val x = 1
        val y = 2
        val z = 3
        val open = 4
        val high = 5
        val low = 6
        val close = 7

        // when
        val point = Point(
            x = x,
            y = y,
            z = z,
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(point).isNotNull
        assertThat(point.x).isEqualTo(x)
        assertThat(point.y).isEqualTo(y)
        assertThat(point.z).isEqualTo(z)
        assertThat(point.open).isEqualTo(open)
        assertThat(point.high).isEqualTo(high)
        assertThat(point.low).isEqualTo(low)
        assertThat(point.close).isEqualTo(close)
    }

    @Test
    fun `long 생성 테스트`() {
        // given
        val x = 1L
        val y = 2L
        val z = 3L
        val open = 4L
        val high = 5L
        val low = 6L
        val close = 7L

        // when
        val point = Point(
            x = x,
            y = y,
            z = z,
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(point).isNotNull
        assertThat(point.x).isEqualTo(x)
        assertThat(point.y).isEqualTo(y)
        assertThat(point.z).isEqualTo(z)
        assertThat(point.open).isEqualTo(open)
        assertThat(point.high).isEqualTo(high)
        assertThat(point.low).isEqualTo(low)
        assertThat(point.close).isEqualTo(close)
    }

    @Test
    fun `float 생성 테스트`() {
        // given
        val x = 1F
        val y = 2F
        val z = 3F
        val open = 4F
        val high = 5F
        val low = 6F
        val close = 7F

        // when
        val point = Point(
            x = x,
            y = y,
            z = z,
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(point).isNotNull
        assertThat(point.x).isEqualTo(x)
        assertThat(point.y).isEqualTo(y)
        assertThat(point.z).isEqualTo(z)
        assertThat(point.open).isEqualTo(open)
        assertThat(point.high).isEqualTo(high)
        assertThat(point.low).isEqualTo(low)
        assertThat(point.close).isEqualTo(close)
    }

    @Test
    fun `double 생성 테스트`() {
        // given
        val x = 1.1
        val y = 2.1
        val z = 3.1
        val open = 4.1
        val high = 5.1
        val low = 6.1
        val close = 7.1

        // when
        val point = Point(
            x = x,
            y = y,
            z = z,
            open = open,
            high = high,
            low = low,
            close = close
        )

        // then
        assertThat(point).isNotNull
        assertThat(point.x).isEqualTo(x)
        assertThat(point.y).isEqualTo(y)
        assertThat(point.z).isEqualTo(z)
        assertThat(point.open).isEqualTo(open)
        assertThat(point.high).isEqualTo(high)
        assertThat(point.low).isEqualTo(low)
        assertThat(point.close).isEqualTo(close)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val point1 = Point(
            x = x1,
            y = y1,
            z = z1,
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val point2 = Point(
            x = x1,
            y = y1,
            z = z1,
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )

        // then
        assertThat(point1).isEqualTo(point2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val point1 = Point(
            x = x1,
            y = y1,
            z = z1,
            open = open1,
            high = high1,
            low = low1,
            close = close1
        )
        val point2 = Point(
            x = x2,
            y = y2,
            z = z2,
            open = open2,
            high = high2,
            low = low2,
            close = close2
        )

        // then
        assertThat(point1).isNotEqualTo(point2)
    }
}
