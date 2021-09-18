package com.wubu.api.stockvalue.minutely.price.entity

import com.wubu.api.common.web.model.CompanyCode
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class MinutelyPriceId(
    @Column(name = "company_id", nullable = false)
    @Convert(converter = CompanyCode.CodeConverter::class)
    var companyCode: CompanyCode,

    @Column(name = "datetime", nullable = false)
    var dateTime: LocalDateTime
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MinutelyPriceId

        if (companyCode != other.companyCode) return false
        if (dateTime != other.dateTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = companyCode.hashCode()
        result = 31 * result + dateTime.hashCode()
        return result
    }
}
