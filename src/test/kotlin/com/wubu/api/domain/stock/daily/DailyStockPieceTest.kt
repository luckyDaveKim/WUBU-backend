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

    @Test
    fun `동등성 비교 테스트`() {
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
        val dailyStockPiece1 = DailyStockPiece(
            date = date,
            price = price,
            volume = volume
        )
        val dailyStockPiece2 = DailyStockPiece(
            date = date,
            price = price,
            volume = volume
        )

        // then
        assertThat(dailyStockPiece1).isEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 date 동등성 비교 실패 테스트`() {
        // given
        val date1 = LocalDate.of(1991, 3, 26)
        val price1 = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume1 = Volume(5L)

        val date2 = LocalDate.of(1991, 3, 27)

        // when
        val dailyStockPiece1 = DailyStockPiece(
            date = date1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            date = date2,
            price = price1,
            volume = volume1
        )

        // then
        assertThat(dailyStockPiece1).isNotEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 price 동등성 비교 실패 테스트`() {
        // given
        val date1 = LocalDate.of(1991, 3, 26)
        val price1 = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume1 = Volume(5L)

        val price2 = OHLC(
            open = Price(5L),
            high = Price(6L),
            low = Price(7L),
            close = Price(8L)
        )

        // when
        val dailyStockPiece1 = DailyStockPiece(
            date = date1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            date = date1,
            price = price2,
            volume = volume1
        )

        // then
        assertThat(dailyStockPiece1).isNotEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 volume 동등성 비교 실패 테스트`() {
        // given
        val date1 = LocalDate.of(1991, 3, 26)
        val price1 = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume1 = Volume(5L)

        val volume2 = Volume(6L)

        // when
        val dailyStockPiece1 = DailyStockPiece(
            date = date1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            date = date1,
            price = price1,
            volume = volume2
        )

        // then
        assertThat(dailyStockPiece1).isNotEqualTo(dailyStockPiece2)
    }
}
