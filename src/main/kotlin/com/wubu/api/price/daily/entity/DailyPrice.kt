package com.wubu.api.price.daily.entity

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "daily_price")
class DailyPrice(
        @EmbeddedId
        var id: DailyPriceId,

        @Column(name = "open", nullable = false)
        var open: Long,

        @Column(name = "high", nullable = false)
        var high: Long,

        @Column(name = "low", nullable = false)
        var low: Long,

        @Column(name = "close", nullable = false)
        var close: Long,

        @Column(name = "diff", nullable = false)
        var diff: Long,

        @Column(name = "volume", nullable = false)
        var volume: Long
)