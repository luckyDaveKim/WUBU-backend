package com.wubu.api.domain.exchangerate.usd.minutely

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.common.web.model.exchangerate.Rate.RateConverter
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class MinutelyUsdExchangeRateId(
    @Column(name = "datetime", nullable = false)
    var dateTime: LocalDateTime,

    @Column(name = "rate", nullable = false)
    @Convert(converter = RateConverter::class)
    var rate: Rate
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MinutelyUsdExchangeRateId

        if (dateTime != other.dateTime) return false
        if (rate != other.rate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dateTime.hashCode()
        result = 31 * result + rate.hashCode()
        return result
    }
}
