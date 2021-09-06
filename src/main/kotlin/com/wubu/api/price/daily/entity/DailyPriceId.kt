package com.wubu.api.price.daily.entity

import com.wubu.api.price.daily.model.Code
import com.wubu.api.price.daily.model.Code.CodeConverter
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
data class DailyPriceId(
        @Convert(converter = CodeConverter::class)
        var code: Code,

        var date: LocalDate
) : Serializable