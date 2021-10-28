package com.wubu.api.domain.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.interfaces.price.daily.DailyPriceConverter.DailyPriceToPointConverter
import com.wubu.api.interfaces.price.daily.DailyPriceRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class DailyPriceServiceImplTest {

    @Mock
    private lateinit var dailyPriceReader: DailyPriceReader

    @Spy
    private lateinit var dailyPriceToPointConverter: DailyPriceToPointConverter

    @InjectMocks
    private lateinit var dailyPriceService: DailyPriceServiceImpl

    private lateinit var dailyPrice1: DailyPrice
    private lateinit var dailyPrice2: DailyPrice
    private lateinit var dailyPrice3: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(
            DailyPriceId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 24)
            ),
            Price(1),
            Price(2),
            Price(3),
            Price(4)
        )
        dailyPrice2 = DailyPrice(
            DailyPriceId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 28)
            ),
            Price(10),
            Price(20),
            Price(30),
            Price(40)
        )
        dailyPrice3 = DailyPrice(
            DailyPriceId(
                CompanyCode("000001"),
                LocalDate.of(1991, 3, 27)
            ),
            Price(100),
            Price(200),
            Price(300),
            Price(400)
        )
    }

    @Test
    fun `데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val pagingReqDto = PagingReqDto()
        val dailyPrices = listOf(dailyPrice1, dailyPrice2, dailyPrice3)
        val dailyPriceRes = DailyPriceRes.of(
            dailyPrices.map(dailyPriceToPointConverter::convert)
        )

        given(
            dailyPriceReader.getDailyPrices(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(dailyPrices)

        // when
        val foundDailyPriceRes = dailyPriceService.retrieveDailyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundDailyPriceRes).isEqualTo(dailyPriceRes)
    }

    @Test
    fun `주 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000000")
        val date = LocalDate.of(2021, 10, 28)
        val dailyPrices = listOf(dailyPrice1, dailyPrice2, dailyPrice3)
        val weekPriceRes = DailyPriceRes.of(
            dailyPrices.map(dailyPriceToPointConverter::convert)
        )

        given(
            dailyPriceReader.getThisWeekPrices(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(dailyPrices)

        // when
        val foundThisWeekPriceRes = dailyPriceService.retrieveThisWeekPrices(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundThisWeekPriceRes).isEqualTo(weekPriceRes)
    }
}
