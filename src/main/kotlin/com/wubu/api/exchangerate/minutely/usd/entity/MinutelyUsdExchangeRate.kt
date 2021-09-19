package com.wubu.api.exchangerate.minutely.usd.entity

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "minutely_exchange_rate")
class MinutelyUsdExchangeRate(
    @EmbeddedId
    var id: MinutelyUsdExchangeRateId
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MinutelyUsdExchangeRate

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
