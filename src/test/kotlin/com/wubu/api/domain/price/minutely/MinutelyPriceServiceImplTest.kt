package com.wubu.api.domain.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.interfaces.price.minutely.MinutelyPriceConverter.MinutelyPriceToPointConverter
import com.wubu.api.interfaces.price.minutely.MinutelyPriceRes
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
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class MinutelyPriceServiceImplTest {

    @Mock
    private lateinit var minutelyPriceReader: MinutelyPriceReader

    @Spy
    private lateinit var minutelyPriceToPointConverter: MinutelyPriceToPointConverter

    @InjectMocks
    private lateinit var minutelyPriceService: MinutelyPriceServiceImpl

    private lateinit var minutelyPrice1: MinutelyPrice
    private lateinit var minutelyPrice2: MinutelyPrice
    private lateinit var minutelyPrice3: MinutelyPrice

    @BeforeEach
    fun setUp() {
        minutelyPrice1 = MinutelyPrice(
            id = MinutelyPriceId(
                companyCode = CompanyCode("000001"),
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0)
            ),
            open = Price(1),
            high = Price(2),
            low = Price(3),
            close = Price(4)
        )

        minutelyPrice2 = MinutelyPrice(
            id = MinutelyPriceId(
                companyCode = CompanyCode("000001"),
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 1)
            ),
            open = Price(10),
            high = Price(20),
            low = Price(30),
            close = Price(40)
        )

        minutelyPrice3 = MinutelyPrice(
            id = MinutelyPriceId(
                companyCode = CompanyCode("000001"),
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 2)
            ),
            open = Price(100),
            high = Price(200),
            low = Price(300),
            close = Price(400)
        )
    }

    @Test
    fun `분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val pagingReqDto = PagingReqDto(
            page = 1,
            pageSize = 2
        )
        val minutelyPrices = listOf(minutelyPrice1, minutelyPrice2, minutelyPrice3)
        val minutelyPriceRes = MinutelyPriceRes.of(
            minutelyPrices.map(minutelyPriceToPointConverter::convert)
        )

        given(
            minutelyPriceReader.getMinutelyPrices(
                companyCode = companyCode,
                pagingReqDto = pagingReqDto
            )
        ).willReturn(minutelyPrices)

        // when
        val foundPointRes = minutelyPriceService.retrieveMinutelyPrices(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundPointRes).isNotNull
        assertThat(foundPointRes).isEqualTo(minutelyPriceRes)
    }

    @Test
    fun `특정일 분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val minutelyPrices = listOf(minutelyPrice1, minutelyPrice2, minutelyPrice3)
        val minutelyPriceRes = MinutelyPriceRes.of(
            minutelyPrices.map(minutelyPriceToPointConverter::convert)
        )

        given(
            minutelyPriceReader.getMinutelyPricesAtDate(
                companyCode = companyCode,
                date = date
            )
        ).willReturn(minutelyPrices)

        // when
        val foundPointRes = minutelyPriceService.retrieveMinutelyPricesAtDate(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundPointRes).isNotNull
        assertThat(foundPointRes).isEqualTo(minutelyPriceRes)
    }
}
