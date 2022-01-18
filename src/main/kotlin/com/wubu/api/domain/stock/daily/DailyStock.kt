package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.OHLC
import com.wubu.api.domain.stock.Stock
import java.time.Instant
import java.time.ZoneOffset
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embedded
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "daily_stock_pieces")
class DailyStock(
    @EmbeddedId
    val id: DailyStockId,

    @Embedded
    override val price: OHLC,

    @Column(name = "volume", nullable = false)
    @Convert(converter = Volume.VolumeConverter::class)
    override val volume: Volume
) : Stock {
    override val companyCode: CompanyCode
        get() = id.companyCode

    override val instant: Instant
        get() = id.date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DailyStock

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
