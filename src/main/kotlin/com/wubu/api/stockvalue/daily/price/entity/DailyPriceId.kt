package com.wubu.api.stockvalue.daily.price.entity

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.CompanyCode.CodeConverter
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class DailyPriceId(
    @Column(name = "company_id", nullable = false)
    @Convert(converter = CodeConverter::class)
    var companyCode: CompanyCode,

    @Column(name = "date", nullable = false)
    var date: LocalDate
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyPriceId

        if (companyCode != other.companyCode) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = companyCode.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}
