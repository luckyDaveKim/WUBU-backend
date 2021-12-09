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
}
