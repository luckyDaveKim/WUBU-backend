package com.wubu.api.interfaces.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import com.wubu.api.domain.stock.StockPrice
import com.wubu.api.domain.stock.daily.DailyStockPiece
import com.wubu.api.domain.stock.daily.DailyStockPieceId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class DailyStockDtoTest {

    private lateinit var stockPiece1: StockPiece
    private lateinit var stockPiece2: StockPiece

    @BeforeEach
    fun setUp() {
        val companyCode1 = CompanyCode("000001")
        val date1 = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId1 = DailyStockPieceId(
            companyCode = companyCode1,
            date = date1
        )
        val price1 = StockPrice(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        val volume1 = Volume(5L)
        stockPiece1 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )

        val companyCode2 = CompanyCode("000001")
        val date2 = LocalDate.of(1991, 3, 26)
        val dailyStockPieceId2 = DailyStockPieceId(
            companyCode = companyCode2,
            date = date2
        )
        val price2 = StockPrice(
            open = Price(6L),
            high = Price(7L),
            low = Price(8L),
            close = Price(9L)
        )
        val volume2 = Volume(10L)
        stockPiece2 = DailyStockPiece(
            id = dailyStockPieceId2,
            price = price2,
            volume = volume2
        )
    }

    @Test
    fun `생성 테스트`() {
        // given
        val stockPieces = listOf(stockPiece1, stockPiece2)

        // when
        val dailyStockDto = DailyStockDto(stockPieces)

        // then
        assertThat(dailyStockDto).isNotNull
        assertThat(dailyStockDto.prices).isEqualTo(stockPieces.map { Point.ofPrice(it) })
        assertThat(dailyStockDto.volumes).isEqualTo(stockPieces.map { Point.ofVolume(it) })
    }
}
