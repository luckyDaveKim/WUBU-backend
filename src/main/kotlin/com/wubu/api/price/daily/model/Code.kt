package com.wubu.api.price.daily.model

import com.wubu.api.common.web.model.SingleValue
import com.wubu.api.price.daily.exception.InvalidLengthCodeException
import javax.persistence.AttributeConverter

data class Code(val code: String) : SingleValue<String>(code) {
    private fun validate(code: String) {
        if (code.length != 6) {
            throw InvalidLengthCodeException(code)
        }
    }

    init {
        validate(code)
    }

    class CodeConverter : AttributeConverter<Code, String> {
        override fun convertToDatabaseColumn(attribute: Code?): String? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: String?): Code? {
            return if (dbData == null) null else Code(dbData)
        }
    }
}