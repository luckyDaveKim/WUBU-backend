package com.wubu.api.domain.jdf

data class JavaDataFrameImpl(
    override val data: List<Double>
) : JavaDataFrame {

    override fun ewm(window: Int): List<Double> {
        return listOf()
    }
}
