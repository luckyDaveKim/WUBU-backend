package com.wubu.api.common.web.model

import com.wubu.api.common.error.exception.InvalidLengthCompanyCodeException
import java.io.Serializable
import javax.persistence.AttributeConverter

data class CompanyCode(private val companyCode: String) : SingleValue<String>(companyCode), Serializable {
    private fun validate(companyCode: String) {
        if (companyCode.length != 6) {
            throw InvalidLengthCompanyCodeException(companyCode)
        }
    }

    init {
        validate(companyCode)
    }

    class CodeConverter : AttributeConverter<CompanyCode, String> {
        override fun convertToDatabaseColumn(attribute: CompanyCode?): String? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: String?): CompanyCode? {
            return if (dbData == null) null else CompanyCode(dbData)
        }
    }
}