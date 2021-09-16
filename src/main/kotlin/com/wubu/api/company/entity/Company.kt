package com.wubu.api.company.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "company_info")
class Company(
    @EmbeddedId
    var id: CompanyId,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "last_update", nullable = false)
    var date: LocalDate
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Company

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
