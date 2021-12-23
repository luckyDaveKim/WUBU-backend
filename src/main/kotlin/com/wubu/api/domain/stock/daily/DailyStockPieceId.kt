package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import java.time.LocalDate

class DailyStockPieceId(
    var companyCode: CompanyCode,
    var date: LocalDate
) {
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
