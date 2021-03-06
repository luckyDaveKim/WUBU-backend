package com.wubu.api.domain.company

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.CompanyCode.CodeConverter
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class CompanyId(
    @Column(name = "id", nullable = false)
    @Convert(converter = CodeConverter::class)
    var companyCode: CompanyCode
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CompanyId

        if (companyCode != other.companyCode) return false

        return true
    }

    override fun hashCode(): Int {
        return companyCode.hashCode()
    }
}
