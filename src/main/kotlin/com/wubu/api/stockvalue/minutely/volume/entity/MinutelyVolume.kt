package com.wubu.api.stockvalue.minutely.volume.entity

import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.common.web.model.stockvalue.Volume.VolumeConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "minutely_volume")
class MinutelyVolume(
    @EmbeddedId
    var id: MinutelyVolumeId,

    @Column(name = "volume", nullable = false)
    @Convert(converter = VolumeConverter::class)
    var volume: Volume
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MinutelyVolume

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
