package com.wubu.api.domain.jdf

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SeriesTest {

    @Test
    fun `생성 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)

        // when
        val series = Series(data)

        // then
        assertThat(series).isNotNull
    }

    @Test
    fun `empty data 생성 테스트`() {
        // given
        val data = emptyList<Double>()

        // when
        val series = Series(data)

        // then
        assertThat(series).isNotNull
    }

    @Test
    fun `data 조회 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)

        // when
        val series = Series(data)

        // then
        assertThat(series.data).isEqualTo(data)
    }

    @Test
    fun `empty data 조회 테스트`() {
        // given
        val data = emptyList<Double>()

        // when
        val series = Series(data)

        // then
        assertThat(series.data).isEqualTo(emptyList<Double>())
    }

    @Test
    fun `plus 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val series1 = Series(data)
        val series2 = Series(data)

        // when
        val series = series1 + series2

        // then
        assertThat(series.data).isEqualTo(listOf(2.0, 4.0, 6.0, 8.0, 10.0))
    }

    @Test
    fun `empty data plus 테스트`() {
        // given
        val data = emptyList<Double>()
        val series1 = Series(data)
        val series2 = Series(data)

        // when
        val series = series1 + series2

        // then
        assertThat(series.data).isEqualTo(emptyList<Double>())
    }
}
