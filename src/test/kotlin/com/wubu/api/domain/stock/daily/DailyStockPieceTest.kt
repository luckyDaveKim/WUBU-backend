package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.OHLC
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

internal class DailyStockPieceTest {

    @Test
    fun `생성 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId = DailyStockPieceId(
            companyCode = companyCode,
            date = date
        )
        val price = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume = Volume(5L)

        // when
        val dailyStockPiece = DailyStockPiece(
            id = dailyStockPieceId,
            price = price,
            volume = volume
        )

        // then
        assertThat(dailyStockPiece).isNotNull
        assertThat(dailyStockPiece.id).isEqualTo(dailyStockPieceId)
        assertThat(dailyStockPiece.x).isEqualTo(date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(dailyStockPiece.price).isEqualTo(price)
        assertThat(dailyStockPiece.volume).isEqualTo(volume)
    }

    @Test
    fun `StockPiece 구현 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId = DailyStockPieceId(
            companyCode = companyCode,
            date = date
        )
        val price = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume = Volume(5L)

        // when
        val stockPiece: StockPiece = DailyStockPiece(
            id = dailyStockPieceId,
            price = price,
            volume = volume
        )

        // then
        assertThat(stockPiece).isNotNull
        assertThat(stockPiece.x).isEqualTo(date.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(stockPiece.price).isEqualTo(price)
        assertThat(stockPiece.volume).isEqualTo(volume)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId = DailyStockPieceId(
            companyCode = companyCode,
            date = date
        )
        val price = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume = Volume(5L)

        // when
        val dailyStockPiece1 = DailyStockPiece(
            id = dailyStockPieceId,
            price = price,
            volume = volume
        )
        val dailyStockPiece2 = DailyStockPiece(
            id = dailyStockPieceId,
            price = price,
            volume = volume
        )

        // then
        assertThat(dailyStockPiece1).isEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 date 동등성 비교 실패 테스트`() {
        // given
        val companyCode1 = CompanyCode("000001")
        val date1 = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId1 = DailyStockPieceId(
            companyCode = companyCode1,
            date = date1
        )
        val price1 = OHLC(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume1 = Volume(5L)

        val companyCode2 = CompanyCode("000002")
        val date2 = LocalDate.of(1991, 3, 27)
        val dailyStockPieceId2 = DailyStockPieceId(
            companyCode = companyCode2,
            date = date2
        )

        // when
        val dailyStockPiece1 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            id = dailyStockPieceId2,
            price = price1,
            volume = volume1
        )

        // then
        assertThat(dailyStockPiece1).isNotEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 price 동등성 비교 실패 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId1 = DailyStockPieceId(
            companyCode = companyCode,
            date = date
        )
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
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price2,
            volume = volume1
        )

        // then
        assertThat(dailyStockPiece1).isNotEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 volume 동등성 비교 실패 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId1 = DailyStockPieceId(
            companyCode = companyCode,
            date = date
        )
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
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume2
        )

        // then
        assertThat(dailyStockPiece1).isNotEqualTo(dailyStockPiece2)
    }
}
