package com.wubu.api.domain.jdf

import kotlin.math.pow
import kotlin.math.roundToLong

data class JavaDataFrameImpl(
    override val data: List<Double>
) : JavaDataFrame {

    override fun ewm(window: Int): List<Double> {
        val result = mutableListOf(data[0])

        for (i: Int in 1 until data.size) {
            val beforeIndex = i - 1
            val avg = (result[beforeIndex] * 0.33) + (data[i] * 0.67)
            result.add(trimNum(avg))
        }

        return result
    }

    private fun trimNum(num: Double): Double {
        val decimal = 2
        val forDecimalNum = (10.0).pow(decimal)

        var result = num * forDecimalNum
        result = result.roundToLong().toDouble()
        result /= forDecimalNum

        return result
    }
}
