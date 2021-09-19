package com.wubu.api.common.web.model.exchangerate

import com.wubu.api.common.error.exception.InvalidRateException
import com.wubu.api.common.web.model.SingleValue
import javax.persistence.AttributeConverter

data class Rate(private val rate: Double) : SingleValue<Double>(rate) {
    private fun validate(rate: Double) {
        if (rate < 0) {
            throw InvalidRateException(rate)
        }
    }

    init {
        validate(rate)
    }

    class RateConverter : AttributeConverter<Rate, Double> {
        override fun convertToDatabaseColumn(attribute: Rate?): Double {
            return attribute?.value ?: 0.0
        }

        override fun convertToEntityAttribute(dbData: Double?): Rate {
            return Rate(dbData ?: 0.0)
        }
    }
}
