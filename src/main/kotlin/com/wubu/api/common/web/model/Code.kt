package com.wubu.api.common.web.model

import com.wubu.api.common.error.exception.InvalidLengthCodeException
import java.io.Serializable
import javax.persistence.AttributeConverter

data class Code(private val code: String) : SingleValue<String>(code), Serializable {
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