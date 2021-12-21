package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.OHLC
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

internal class DailyStockPieceTest {

    @Test
    fun `생성 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val price = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume = Volume(5L)

        // when
        val dailyStockPiece = DailyStockPiece(
            date = date,
            price = price,
            volume = volume
        )

        // then
        assertThat(dailyStockPiece).isNotNull
        assertThat(dailyStockPiece.x).isEqualTo(date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(dailyStockPiece.price).isEqualTo(price)
        assertThat(dailyStockPiece.volume).isEqualTo(volume)
    }
}
