package com.wubu.api.common.web.dto

import com.wubu.api.common.web.model.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointDtoTest {

    @Test
    fun `생성 테스트`() {
        // given
        val point = Point(
                x = 1,
                y = 2,
                z = 3,
                open = 4,
                high = 5,
                low = 6,
                close = 7
        )

        // when
        val pointDto = PointDto.of(point)

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

}