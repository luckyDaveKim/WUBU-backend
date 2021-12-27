package com.wubu.api.infra.stock.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.domain.stock.StockPrice
import com.wubu.api.domain.stock.daily.DailyStockPiece
import com.wubu.api.domain.stock.daily.DailyStockPieceId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class DailyStockPieceReaderImplTest {

    @Mock
    private lateinit var dailyStockPiecesRepository: DailyStockPiecesRepository

    @InjectMocks
    private lateinit var dailyStockPieceReader: DailyStockPieceReaderImpl

    private lateinit var dailyStockPiece1: DailyStockPiece
    private lateinit var dailyStockPiece2: DailyStockPiece

    @BeforeEach
    fun setUp() {
        dailyStockPiece1 = DailyStockPiece(
            id = DailyStockPieceId(
                companyCode = CompanyCode("000001"),
                date = LocalDate.of(1991, 3, 26)
            ),
            price = StockPrice(
                open = Price(1L),
                high = Price(2L),
                low = Price(3L),
                close = Price(4L)
            ),
            volume = Volume(5L)
        )

        dailyStockPiece2 = DailyStockPiece(
            id = DailyStockPieceId(
                companyCode = CompanyCode("000002"),
                date = LocalDate.of(1991, 3, 27)
            ),
            price = StockPrice(
                open = Price(10L),
                high = Price(20L),
                low = Price(30L),
                close = Price(40L)
            ),
            volume = Volume(50L)
        )
    }

    @Test
    fun `Price 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val pagingReqDto = PagingReqDto()
        val dailyStockPieces = listOf(dailyStockPiece1, dailyStockPiece2)
        val reversedDailyStockPieces = dailyStockPieces.reversed()

        given(
            dailyStockPiecesRepository.findAllById_CompanyCodeOrderById_DateDesc(
                companyCode = companyCode,
                pageable = pagingReqDto.getPageable()
            )
        ).willReturn(reversedDailyStockPieces)

        // when
        val foundDailyStockPieces = dailyStockPieceReader.getDailyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundDailyStockPieces).isEqualTo(dailyStockPieces)
    }
}