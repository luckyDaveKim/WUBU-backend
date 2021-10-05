package com.wubu.api.domain.exchangerate.usd.daily

import com.wubu.api.common.web.model.exchangerate.Rate
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class DailyUsdExchangeRateId(
    @Column(name = "date", nullable = false)
    var date: LocalDate,

    @Column(name = "rate", nullable = false)
    @Convert(converter = Rate.RateConverter::class)
    var rate: Rate
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyUsdExchangeRateId

        if (date != other.date) return false
        if (rate != other.rate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + rate.hashCode()
        return result
    }
}
