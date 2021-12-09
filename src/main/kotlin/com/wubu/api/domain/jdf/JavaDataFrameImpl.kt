package com.wubu.api.domain.jdf

import kotlin.math.exp
import kotlin.math.ln

data class JavaDataFrameImpl(
    override val data: List<Double>
) : JavaDataFrame {

    override fun ema(window: Int): Series {
        if (window < 0) throw IllegalArgumentException(String.format("window는 음수일 수 없습니다. '%d", window))

        if (data.isEmpty()) return Series(emptyList())

        val newData = mutableListOf<Double>()
        val alpha = 2.0 / (window + 1)
        var p = 0.0
        var a = 0.0
        for (i in data.indices) {
            p = data[i] + ((1 - alpha) * p)
            a += exp(i * ln(1 - alpha))
            newData.add(p / a)
        }

        return Series(newData)
    }
}
