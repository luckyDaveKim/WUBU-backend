package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class DailyStockPieceId(
    @Column(name = "company_id", nullable = false)
    @Convert(converter = CompanyCode.CodeConverter::class)
    var companyCode: CompanyCode,

    @Column(name = "date", nullable = false)
    var date: LocalDate
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyStockPieceId

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
