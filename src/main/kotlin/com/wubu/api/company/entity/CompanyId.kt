package com.wubu.api.company.entity

import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.Code.CodeConverter
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class CompanyId(
        @Column(name = "code", nullable = false)
        @Convert(converter = CodeConverter::class)
        var code: Code
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CompanyId

        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}