package com.wubu.api.application.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.domain.price.minutely.MinutelyPrice
import com.wubu.api.domain.price.minutely.MinutelyPriceId
import com.wubu.api.infra.price.minutely.MinutelyPriceRepository
import com.wubu.api.interfaces.price.minutely.MinutelyPriceConverter.MinutelyPriceToPointConverter
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
internal class MinutelyPriceFindServiceTest {

    @Mock
    private lateinit var minutelyPriceRepository: MinutelyPriceRepository

    @Spy
    private lateinit var minutelyPriceToPointConverter: MinutelyPriceToPointConverter

    @InjectMocks
    private lateinit var minutelyPriceFindService: MinutelyPriceFindService

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

        val minutelyPrices = listOf(minutelyPrice2, minutelyPrice3)
        val reversedMinutelyPrices = minutelyPrices.reversed()
        val pointResDto = PointResDto.of(minutelyPrices.map(minutelyPriceToPointConverter::convert))

        given(
            minutelyPriceRepository.findAllById_CompanyCodeOrderById_DateTimeDesc(
                companyCode = companyCode,
                pageable = pagingReqDto.getPageable()
            )
        ).willReturn(reversedMinutelyPrices)

        // when
        val foundPointResDto = minutelyPriceFindService.findMinutelyStockValue(
            companyCode = companyCode,
            pagingReqDto = pagingReqDto
        )

        // then
        assertThat(foundPointResDto).isNotNull
        assertThat(foundPointResDto).isEqualTo(pointResDto)
    }

    @Test
    fun `특정일 분별 데이터 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val date = LocalDate.of(1991, 3, 26)
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()
        val minutelyPrices = listOf(minutelyPrice2, minutelyPrice3)
        val reversedMinutelyPrices = minutelyPrices.reversed()
        val points = minutelyPrices.map(minutelyPriceToPointConverter::convert)
        val pointResDto = PointResDto.of(points)

        given(
            minutelyPriceRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )
        ).willReturn(reversedMinutelyPrices)

        // when
        val foundPointResDto = minutelyPriceFindService.findMinutelyStockValueAtDate(
            companyCode = companyCode,
            date = date
        )

        // then
        assertThat(foundPointResDto).isNotNull
        assertThat(foundPointResDto).isEqualTo(pointResDto)
    }
}
