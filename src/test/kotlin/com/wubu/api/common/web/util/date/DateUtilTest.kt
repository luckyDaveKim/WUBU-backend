package com.wubu.api.common.web.util.date

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate

internal class DateUtilTest {

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dateUtil = DateUtil()

        // then
        assertThat(dateUtil).isNotNull
    }

    @Test
    fun getStartDateOfWeekTest() {
        val date = LocalDate.of(1991, 3, 26)

        for (i: Long in 0..6L) {
            // given
            val targetDate = date.minusDays(i)

            // when
            val startDateOfWeek = DateUtil.getStartDateOfWeek(targetDate)

            // then
            assertThat(startDateOfWeek.dayOfWeek).isEqualTo(DayOfWeek.MONDAY)
            assertThat(startDateOfWeek).isBetween(
                targetDate.minusDays(6),
                targetDate
            )
        }
    }
}
