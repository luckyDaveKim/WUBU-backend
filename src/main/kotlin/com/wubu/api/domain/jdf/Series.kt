package com.wubu.api.domain.jdf

class Series(
    val data: List<Double>
) {
    operator fun plus(otherSeries: Series): Series {
        return Series(emptyList())
    }
}
