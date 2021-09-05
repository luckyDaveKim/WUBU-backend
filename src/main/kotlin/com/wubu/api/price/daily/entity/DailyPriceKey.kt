package com.wubu.api.price.daily.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Embeddable

@Embeddable
data class DailyPriceKey(
        var code: String,
        var date: LocalDate
) : Serializable