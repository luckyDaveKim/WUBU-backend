package com.wubu.api.infra.price.minutely

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.domain.price.minutely.MinutelyPrice
import com.wubu.api.domain.price.minutely.MinutelyPriceId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@ContextConfiguration(initializers = [ConfigDataApplicationContextInitializer::class])
@ActiveProfiles("test")
internal class MinutelyPriceRepositoryTest(
    @Autowired
    private val minutelyPriceRepository: MinutelyPriceRepository
) {

    private lateinit var minutelyPrice1: MinutelyPrice
    private lateinit var minutelyPrice2: MinutelyPrice
    private lateinit var minutelyPrice3: MinutelyPrice

    @BeforeEach
    fun setUp() {
        minutelyPriceRepository.deleteAll()

        minutelyPrice1 = MinutelyPrice(
            MinutelyPriceId(
                CompanyCode("000001"),
                LocalDateTime.of(1991, 3, 26, 9, 0)
            ),
            Price(1),
            Price(2),
            Price(3),
            Price(4)
        )
        minutelyPrice2 = MinutelyPrice(
            MinutelyPriceId(
                CompanyCode("000001"),
                LocalDateTime.of(1991, 3, 26, 9, 1)
            ),
            Price(10),
            Price(20),
            Price(30),
            Price(40)
        )
        minutelyPrice3 = MinutelyPrice(
            MinutelyPriceId(
                CompanyCode("000001"),
                LocalDateTime.of(1991, 3, 26, 9, 2)
            ),
            Price(100),
            Price(200),
            Price(300),
            Price(400)
        )

        minutelyPriceRepository.save(minutelyPrice1)
        minutelyPriceRepository.save(minutelyPrice2)
        minutelyPriceRepository.save(minutelyPrice3)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given

        // when
        val foundMinutelyPrice = minutelyPriceRepository.findById(minutelyPrice1.id)

        // then
        assertThat(foundMinutelyPrice.get()).isEqualTo(minutelyPrice1)
    }

    @Test
    fun `코드 기준 리스트 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val page = 1
        val pageSize = 2
        val pageable = PagingReqDto(
            page = page,
            pageSize = pageSize
        ).getPageable()

        // when
        val foundMinutelyPrices =
            minutelyPriceRepository.findAllById_CompanyCodeOrderById_DateTimeDesc(
                companyCode = companyCode,
                pageable = pageable
            )

        // then
        assertThat(foundMinutelyPrices).isNotNull
        assertThat(foundMinutelyPrices.size).isEqualTo(pageSize)
        assertThat(foundMinutelyPrices[0]).isEqualTo(minutelyPrice3)
        assertThat(foundMinutelyPrices[1]).isEqualTo(minutelyPrice2)
    }

    @Test
    fun `코드 및 날짜 기준 리스트 조회 테스트`() {
        // given
        val companyCode = CompanyCode("000001")
        val today = LocalDate.now()
        val afterEqualDateTime = today.atStartOfDay()
        val beforeDateTime = today.plusDays(1).atStartOfDay()

        for (minutelyPrice in getPricesYesterdayToTomorrow()) {
            minutelyPriceRepository.save(minutelyPrice)
        }

        // when
        val foundMinutelyPrices =
            minutelyPriceRepository.findAllById_CompanyCodeAndId_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                companyCode = companyCode,
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )

        // then
        assertThat(foundMinutelyPrices).isNotNull
        assertThat(foundMinutelyPrices.size).isEqualTo(1)
    }

    private fun getPricesYesterdayToTomorrow(): List<MinutelyPrice> {
        val prices = emptyList<MinutelyPrice>().toMutableList()
        val today = LocalDateTime.now()

        for (i: Long in -1..1L) {
            val targetDateTime = today.plusDays(i)
            val minutelyPrice = MinutelyPrice(
                id = MinutelyPriceId(companyCode = CompanyCode("000001"), dateTime = targetDateTime),
                open = Price(1),
                high = Price(2),
                low = Price(3),
                close = Price(4)
            )
            prices.add(minutelyPrice)
        }

        return prices
    }
}
