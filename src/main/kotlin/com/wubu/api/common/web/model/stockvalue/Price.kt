package com.wubu.api.common.web.model.stockvalue

import com.wubu.api.common.error.exception.InvalidPriceException
import com.wubu.api.common.web.model.SingleValue
import javax.persistence.AttributeConverter

data class Price(private val price: Long) : SingleValue<Long>(price), StockValue {
    private fun validate(price: Long) {
        if (price < 0) {
            throw InvalidPriceException(price)
        }
    }

    init {
        validate(price)
    }

    class PriceConverter : AttributeConverter<Price, Long> {
        override fun convertToDatabaseColumn(attribute: Price?): Long {
            return attribute?.value ?: 0L
        }

        override fun convertToEntityAttribute(dbData: Long?): Price {
            return Price(dbData ?: 0L)
        }
    }
}