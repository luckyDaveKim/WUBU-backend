package com.wubu.api.common.web.model.stockvalue

import com.wubu.api.common.exception.InvalidVolumeException
import javax.persistence.AttributeConverter

data class Volume(private val volume: Long) : StockValue(volume) {
    private fun validate(volume: Long) {
        if (volume < 0) {
            throw InvalidVolumeException(volume)
        }
    }

    init {
        validate(volume)
    }

    class VolumeConverter : AttributeConverter<Volume, Long> {
        override fun convertToDatabaseColumn(attribute: Volume?): Long {
            return attribute?.value ?: 0L
        }

        override fun convertToEntityAttribute(dbData: Long?): Volume {
            return Volume(dbData ?: 0L)
        }
    }
}
