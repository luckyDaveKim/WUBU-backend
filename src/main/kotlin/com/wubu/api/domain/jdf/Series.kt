package com.wubu.api.domain.jdf

import kotlin.math.max

class Series(
    val data: List<Double>
) {
    operator fun plus(otherSeries: Series): Series {
        val newData = mutableListOf<Double>()
        val maxSize = max(data.size, otherSeries.data.size)
        for (i in 0 until maxSize) {
            val curVal = data.getOrElse(i) { 0.0 }
            val otherVal = otherSeries.data.getOrElse(i) { 0.0 }
            newData.add(curVal + otherVal)
        }
        return Series(newData)
    }

    operator fun minus(otherSeries: Series): Series {
        return Series(listOf(0.0, 0.0, 0.0, 0.0, 0.0))
    }
}
