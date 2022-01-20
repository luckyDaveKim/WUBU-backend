package com.wubu.api.interfaces.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.Point
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.OHLC
import com.wubu.api.domain.stock.Stock
import com.wubu.api.domain.stock.daily.DailyStock
import com.wubu.api.domain.stock.daily.DailyStockId
import com.wubu.api.interfaces.stock.StockRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class StockResTest {

    private lateinit var stock1: Stock
    private lateinit var stock2: Stock

    @BeforeEach
    fun setUp() {
        val companyCode1 = CompanyCode("000001")
        val date1 = LocalDate.of(1991, 3, 26)
        val dailyStockId1 = DailyStockId(
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
        stock1 = DailyStock(
            id = dailyStockId1,
            price = price1,
            volume = volume1
        )

        val companyCode2 = CompanyCode("000001")
        val date2 = LocalDate.of(1991, 3, 26)
        val dailyStockId2 = DailyStockId(
            companyCode = companyCode2,
            date = date2
        )
        val price2 = OHLC(
            open = Price(6L),
            high = Price(7L),
            low = Price(8L),
            close = Price(9L)
        )
        val volume2 = Volume(10L)
        stock2 = DailyStock(
            id = dailyStockId2,
            price = price2,
            volume = volume2
        )
    }

    @Test
    fun `생성 테스트`() {
        // given
        val stockPieces = listOf(stock1, stock2)

        // when
        val stockRes = StockRes(stockPieces)

        // then
        assertThat(stockRes).isNotNull
        assertThat(stockRes.prices).isEqualTo(stockPieces.map { Point.ofPrice(it) })
        assertThat(stockRes.volumes).isEqualTo(stockPieces.map { Point.ofVolume(it) })
    }
}
