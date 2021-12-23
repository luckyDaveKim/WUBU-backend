package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPiece
import com.wubu.api.domain.stock.StockPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneOffset

internal class DailyStockPieceTest {

    private lateinit var companyCode1: CompanyCode
    private lateinit var companyCode2: CompanyCode
    private lateinit var date1: LocalDate
    private lateinit var date2: LocalDate
    private lateinit var dailyStockPieceId1: DailyStockPieceId
    private lateinit var dailyStockPieceId2: DailyStockPieceId
    private lateinit var price1: StockPrice
    private lateinit var price2: StockPrice
    private lateinit var volume1: Volume
    private lateinit var volume2: Volume

    @BeforeEach
    fun setUp() {
        companyCode1 = CompanyCode("000001")
        date1 = LocalDate.of(1991, 3, 26)
        dailyStockPieceId1 = DailyStockPieceId(
            companyCode = companyCode1,
            date = date1
        )
        price1 = StockPrice(
            open = Price(1L),
            high = Price(2L),
            low = Price(3L),
            close = Price(4L)
        )
        volume1 = Volume(5L)

        companyCode2 = CompanyCode("000002")
        date2 = LocalDate.of(1991, 3, 27)
        dailyStockPieceId2 = DailyStockPieceId(
            companyCode = companyCode2,
            date = date2
        )
        price2 = StockPrice(
            open = Price(10L),
            high = Price(20L),
            low = Price(30L),
            close = Price(40L)
        )
        volume2 = Volume(50L)
    }

    @Test
    fun `생성 테스트`() {
        // given

        // when
        val dailyStockPiece = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )

        // then
        assertThat(dailyStockPiece).isNotNull
        assertThat(dailyStockPiece.id).isEqualTo(dailyStockPieceId1)
        assertThat(dailyStockPiece.x).isEqualTo(date1.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(dailyStockPiece.price).isEqualTo(price1)
        assertThat(dailyStockPiece.volume).isEqualTo(volume1)
    }

    @Test
    fun `StockPiece 구현 테스트`() {
        // given

        // when
        val stockPiece: StockPiece = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )

        // then
        assertThat(stockPiece).isNotNull
        assertThat(stockPiece.x).isEqualTo(date1.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        assertThat(stockPiece.price).isEqualTo(price1)
        assertThat(stockPiece.volume).isEqualTo(volume1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val dailyStockPiece1 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )

        // then
        assertThat(dailyStockPiece1).isEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 price 동등성 비교 테스트`() {
        // given

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
        assertThat(dailyStockPiece1).isEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 volume 동등성 비교 테스트`() {
        // given

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
        assertThat(dailyStockPiece1).isEqualTo(dailyStockPiece2)
    }

    @Test
    fun `다른 id 동등성 비교 실패 테스트`() {
        // given

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
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val dailyStockPiece1 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )
        val dailyStockPiece2 = DailyStockPiece(
            id = dailyStockPieceId1,
            price = price1,
            volume = volume1
        )

        // then
        assertThat(dailyStockPiece1.hashCode()).isEqualTo(dailyStockPiece2.hashCode())
    }

    @Test
    fun `다른 price hashCode 비교 테스트`() {
        // given

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
        assertThat(dailyStockPiece1.hashCode()).isEqualTo(dailyStockPiece2.hashCode())
    }

    @Test
    fun `다른 volume hashCode 비교 테스트`() {
        // given

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
        assertThat(dailyStockPiece1.hashCode()).isEqualTo(dailyStockPiece2.hashCode())
    }

    @Test
    fun `다른 id hashCode 비교 실패 테스트`() {
        // given

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
        assertThat(dailyStockPiece1.hashCode()).isNotEqualTo(dailyStockPiece2.hashCode())
    }
}
