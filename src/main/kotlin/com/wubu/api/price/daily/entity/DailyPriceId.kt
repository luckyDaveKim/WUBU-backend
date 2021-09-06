package com.wubu.api.price.daily.entity

import com.wubu.api.price.daily.model.Code
import com.wubu.api.price.daily.model.Code.CodeConverter
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
data class DailyPriceId(
        @Column(name = "code", nullable = false)
        @Convert(converter = CodeConverter::class)
        var code: Code,

        @Column(name = "date", nullable = false)
        var date: LocalDate
) : Serializable