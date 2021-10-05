package com.wubu.api.domain.volume.minutely

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.CompanyCode.CodeConverter
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class MinutelyVolumeId(
    @Column(name = "company_id", nullable = false)
    @Convert(converter = CodeConverter::class)
    var companyCode: CompanyCode,

    @Column(name = "datetime", nullable = false)
    var dateTime: LocalDateTime
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MinutelyVolumeId

        if (companyCode != other.companyCode) return false
        if (dateTime != other.dateTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = companyCode.hashCode()
        result = 31 * result + dateTime.hashCode()
        return result
    }
}
