package com.wubu.api.infra.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.OHLC
import com.wubu.api.domain.stock.daily.DailyStock
import com.wubu.api.domain.stock.daily.DailyStockId
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
    private lateinit var dailyStockPiece1: DailyStock
    private lateinit var dailyStockPiece2: DailyStock
    private lateinit var dailyStockPiece3: DailyStock

    @BeforeEach
    fun setUp() {
        dailyStockPiece1 = DailyStock(
            id = DailyStockId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 26)
            ),
            price = OHLC(
                open = Price(1),
                high = Price(2),
                low = Price(3),
                close = Price(4),
            ),
            volume = Volume(5)
        )
        dailyStockPiece2 = DailyStock(
            id = DailyStockId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 27)
            ),
            price = OHLC(
                open = Price(10),
                high = Price(20),
                low = Price(30),
                close = Price(40),
            ),
            volume = Volume(50)
        )
        dailyStockPiece3 = DailyStock(
            id = DailyStockId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 28)
            ),
            price = OHLC(
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

    @Test
    fun `code 기준 리스트 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val pageable = PagingReqDto().getPageable()

        // when
        val foundDailyStockPieces = dailyStockPiecesRepository.findAllById_CompanyCodeOrderById_DateDesc(
            companyCode = companyCode,
            pageable = pageable
        )

        // then
        assertThat(foundDailyStockPieces[0]).isEqualTo(dailyStockPiece3)
        assertThat(foundDailyStockPieces[1]).isEqualTo(dailyStockPiece2)
        assertThat(foundDailyStockPieces[2]).isEqualTo(dailyStockPiece1)
    }
}
