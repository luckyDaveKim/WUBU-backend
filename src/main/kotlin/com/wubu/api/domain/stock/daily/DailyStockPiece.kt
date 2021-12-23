package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import com.wubu.api.domain.stock.StockPrice
import java.time.ZoneOffset
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embedded
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "daily_stock_pieces")
class DailyStockPiece(
    @EmbeddedId
    val id: DailyStockPieceId,

    @Embedded
    override val price: StockPrice,

    @Column(name = "volume", nullable = false)
    @Convert(converter = Volume.VolumeConverter::class)
    override val volume: Volume
) : StockPiece {
    override val x: Number
        get() = id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyStockPiece

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
