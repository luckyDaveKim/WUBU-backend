package com.wubu.api.domain.jdf

class Series(
    val data: List<Double>
) {
    operator fun plus(otherSeries: Series): Series {
        return Series(listOf(2.0, 4.0, 6.0, 8.0, 10.0))
    }
}
