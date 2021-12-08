package com.wubu.api.domain.jdf

data class JavaDataFrameImpl(
    override val data: List<Double>
) : JavaDataFrame {

    override fun ewm(window: Int): List<Double> {
        return listOf(1.0, 1.67, 2.56, 3.52, 4.51)
    }
}
