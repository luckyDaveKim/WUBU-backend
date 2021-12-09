package com.wubu.api.domain.jdf

import org.assertj.core.api.Assertions.assertThat
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
        assertThat(ema).isEqualTo(
            listOf(
                1.0,
                1.6666666666666667,
                2.4285714285714284,
                3.2666666666666666,
                4.161290322580645
            )
        )
    }

    @Test
    fun `empty data ema 테스트`() {
        // given
        val data = emptyList<Double>()
        val jdf = JavaDataFrameImpl(data)

        // when
        val ema = jdf.ema(window = 3)

        // then
        assertThat(ema).isEqualTo(emptyList<Double>())
    }
}
