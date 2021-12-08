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
    fun `ewm 테스트`() {
        // given
        val data = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val jdf = JavaDataFrameImpl(data)

        // when
        val ewm = jdf.ewm(window = 3)

        // then
        assertThat(ewm).isEqualTo(listOf(1.0, 1.67, 2.56, 3.52, 4.51))
    }
}
