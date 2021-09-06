package com.wubu.api.price.daily.model

import com.wubu.api.common.web.model.SingleValue
import com.wubu.api.price.daily.exception.InvalidVolumeException
import javax.persistence.AttributeConverter

data class Volume(val volume: Long) : SingleValue<Long>(volume) {
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