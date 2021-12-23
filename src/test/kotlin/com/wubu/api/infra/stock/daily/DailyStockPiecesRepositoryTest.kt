package com.wubu.api.infra.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPrice
import com.wubu.api.domain.stock.daily.DailyStockPiece
import com.wubu.api.domain.stock.daily.DailyStockPieceId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDate

@SpringBootTest
@ContextConfiguration(initializers = [ConfigDataApplicationContextInitializer::class])
@ActiveProfiles("test")
internal class DailyStockPiecesRepositoryTest(
    @Autowired
    private val dailyStockPiecesRepository: DailyStockPiecesRepository
) {
    private lateinit var dailyStockPiece1: DailyStockPiece
    private lateinit var dailyStockPiece2: DailyStockPiece
    private lateinit var dailyStockPiece3: DailyStockPiece

    @BeforeEach
    fun setUp() {
        dailyStockPiece1 = DailyStockPiece(
            id = DailyStockPieceId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 24)
            ),
            price = StockPrice(
                open = Price(1),
                high = Price(2),
                low = Price(3),
                close = Price(4),
            ),
            volume = Volume(5)
        )
        dailyStockPiece2 = DailyStockPiece(
            id = DailyStockPieceId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 28)
            ),
            price = StockPrice(
                open = Price(10),
                high = Price(20),
                low = Price(30),
                close = Price(40),
            ),
            volume = Volume(50)
        )
        dailyStockPiece3 = DailyStockPiece(
            id = DailyStockPieceId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 27)
            ),
            price = StockPrice(
                open = Price(100),
                high = Price(200),
                low = Price(300),
                close = Price(400),
            ),
            volume = Volume(500)
        )

        dailyStockPiecesRepository.deleteAll()

        dailyStockPiecesRepository.save(dailyStockPiece1)
        dailyStockPiecesRepository.save(dailyStockPiece2)
        dailyStockPiecesRepository.save(dailyStockPiece3)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given
        val id = dailyStockPiece1.id

        // when
        val foundDailPrice = dailyStockPiecesRepository.findById(id)

        // then
        assertThat(foundDailPrice.get()).isEqualTo(dailyStockPiece1)
    }
}
