package com.wubu.api.domain.jdf

interface JavaDataFrame {
    val data: List<Double>

    /**
     * 지수 이동평균 (Exponential Moving Average)
     */
    fun ewm(window: Int): List<Double>
}
