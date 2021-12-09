package com.wubu.api.domain.jdf

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class JavaDataFrameImplTest {

    @Test
    fun `생성 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)

        // when
        val jdf = JavaDataFrameImpl(data)

        // then
        assertThat(jdf).isNotNull
    }

    @Test
    fun `empty data 생성 테스트`() {
        // given
        val data = emptyList<Double>()

        // when
        val jdf = JavaDataFrameImpl(data)

        // then
        assertThat(jdf).isNotNull
    }

    @Test
    fun `ema 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val jdf = JavaDataFrameImpl(data)

        // when
        val ema = jdf.ema(window = 3)

        // then
        val series = Series(
            listOf(
                1.0,
                1.6666666666666667,
                2.4285714285714284,
                3.2666666666666666,
                4.161290322580645
            )
        )
        assertThat(ema).isEqualTo(series)
    }

    @Test
    fun `empty data ema 테스트`() {
        // given
        val data = emptyList<Double>()
        val jdf = JavaDataFrameImpl(data)

        // when
        val ema = jdf.ema(window = 3)

        // then
        assertThat(ema).isEqualTo(Series(emptyList()))
    }

    @Test
    fun `음수 window ema 실패 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val jdf = JavaDataFrameImpl(data)

        // when

        // then
        assertThatThrownBy { jdf.ema(window = -1) }
    }

    @Test
    fun `ema 더하기 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val jdf = JavaDataFrameImpl(data)
        val ema1 = jdf.ema(window = 3)
        val ema2 = jdf.ema(window = 3)

        // when
        val ema = ema1 + ema2

        // then
        val series = Series(
            listOf(
                2.0,
                3.3333333333333335,
                4.857142857142857,
                6.533333333333333,
                8.32258064516129
            )
        )
        assertThat(ema).isEqualTo(series)
    }

    @Test
    fun `ema 빼기 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val jdf = JavaDataFrameImpl(data)
        val ema1 = jdf.ema(window = 3)
        val ema2 = jdf.ema(window = 3)

        // when
        val ema = ema1 - ema2

        // then
        val series = Series(
            listOf(0.0, 0.0, 0.0, 0.0, 0.0)
        )
        assertThat(ema).isEqualTo(series)
    }
}
