package com.wubu.api.common.web.util.date

import java.time.DayOfWeek
import java.time.LocalDate

class DateUtil {

    companion object {
        fun getStartDateOfWeek(date: LocalDate): LocalDate {
            var mondayDate = date
            while (mondayDate.dayOfWeek != DayOfWeek.MONDAY) {
                mondayDate = mondayDate.minusDays(1)
            }
            return mondayDate
        }

        fun getStartDateOfNextWeek(date: LocalDate): LocalDate {
            return getStartDateOfWeek(date).plusDays(7)
        }
    }
}
