package com.wubu.api.stockvalue.minutely.volume.entity

import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.common.web.model.stockvalue.Volume.VolumeConverter
import java.util.stream.Collector
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

    companion object {
        fun toStacked(): Collector<MinutelyVolume, MutableList<MinutelyVolume>, MutableList<MinutelyVolume>> {
            return Collector.of(
                { ArrayList() },
                { stackedList, volume ->
                    if (stackedList.isEmpty()) {
                        stackedList.add(volume)
                    } else {
                        stackedList.add(stackedList.last() + volume)
                    }
                },
                { stackedList1, stackedList2 ->
                    if (stackedList1.isNotEmpty()) {
                        val baseValue = stackedList1.last()
                        stackedList2.replaceAll { value -> baseValue + value }
                    }
                    stackedList1.addAll(stackedList2)
                    stackedList1
                }
            )
        }
    }

    operator fun plus(minutelyVolume: MinutelyVolume): MinutelyVolume {
        return MinutelyVolume(
            id = minutelyVolume.id,
            volume = Volume(volume.value + minutelyVolume.volume.value)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MinutelyVolume

        if (id != other.id) return false
        if (volume != other.volume) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + volume.hashCode()
        return result
    }
}
