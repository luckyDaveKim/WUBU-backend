package com.wubu.api.common.web.model

import com.wubu.api.common.error.exception.InvalidPercentageException

data class Percentage(private val percentage: Double) : SingleValue<Double>(percentage) {
    private fun validate(percentage: Double) {
        if (percentage < 0) {
            throw InvalidPercentageException(percentage)
        }
    }

    init {
        validate(percentage)
    }
}
