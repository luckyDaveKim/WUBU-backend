package com.wubu.api.company.entity

import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.Code.CodeConverter
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
data class CompanyId(
        @Column(name = "code", nullable = false)
        @Convert(converter = CodeConverter::class)
        var code: Code
) : Serializable