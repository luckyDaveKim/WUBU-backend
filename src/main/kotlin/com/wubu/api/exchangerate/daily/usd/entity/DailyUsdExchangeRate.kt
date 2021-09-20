package com.wubu.api.exchangerate.daily.usd.entity

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "daily_exchange_rate")
class DailyUsdExchangeRate(
    @EmbeddedId
    var id: DailyUsdExchangeRateId
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyUsdExchangeRate

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
