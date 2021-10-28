package com.wubu.api.infra.price.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.util.date.DateUtil
import com.wubu.api.domain.price.daily.DailyPrice
import com.wubu.api.domain.price.daily.DailyPriceId
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
internal class DailyPriceRepositoryTest(
    @Autowired
    private val dailyPriceRepository: DailyPriceRepository
) {

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

        dailyPriceRepository.deleteAll()

        dailyPriceRepository.save(dailyPrice1)
        dailyPriceRepository.save(dailyPrice2)
        dailyPriceRepository.save(dailyPrice3)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given
        val id = dailyPrice1.id

        // when
        val foundDailPrice = dailyPriceRepository.findById(id)

        // then
        assertThat(foundDailPrice.get()).isEqualTo(dailyPrice1)
    }

    @Test
    fun `code 기준 리스트 조회 테스트`() {
        // given
        val code = dailyPrice1.id.companyCode
        val pageable = PagingReqDto().getPageable()

        // when
        val foundDailyPrices = dailyPriceRepository.findAllByIdCompanyCodeOrderByIdDateDesc(code, pageable)

        // then
        assertThat(foundDailyPrices[0]).isEqualTo(dailyPrice2)
        assertThat(foundDailyPrices[1]).isEqualTo(dailyPrice3)
        assertThat(foundDailyPrices[2]).isEqualTo(dailyPrice1)
    }

    @Test
    fun `페이징 테스트`() {
        // given
        val code = dailyPrice1.id.companyCode
        val page = 2
        val pageSize = 1
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailyPrices = dailyPriceRepository.findAllByIdCompanyCodeOrderByIdDateDesc(code, pageable)

        // then
        assertThat(foundDailyPrices.size).isEqualTo(pageSize)
        assertThat(foundDailyPrices[0]).isEqualTo(dailyPrice3)
    }

    @Test
    fun `페이징 사이즈 테스트`() {
        // given
        val code = dailyPrice1.id.companyCode
        val page = 1
        val pageSize = 2
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailyPrices = dailyPriceRepository.findAllByIdCompanyCodeOrderByIdDateDesc(code, pageable)

        // then
        assertThat(foundDailyPrices.size).isEqualTo(pageSize)

        assertThat(foundDailyPrices[0]).isEqualTo(dailyPrice2)
        assertThat(foundDailyPrices[1]).isEqualTo(dailyPrice3)
    }

    @Test
    fun `코드 및 날짜 기준 리스트 조회 테스트`() {
        // given
        val code = dailyPrice2.id.companyCode
        val today = LocalDate.now()
        val startDateOfWeek = DateUtil.getStartDateOfWeek(today)
        val startDateOfNextWeek = DateUtil.getStartDateOfNextWeek(today)
        val thisWeekDataSize = today.dayOfWeek.value

        for (dailyPrice in getPricesBefore6DaysUntilToday()) {
            dailyPriceRepository.save(dailyPrice)
        }

        // when
        val foundThisWeekDailyPrices =
            dailyPriceRepository.findAllByIdCompanyCodeAndId_DateGreaterThanEqualAndId_DateLessThanOrderByIdDateAsc(
                companyCode = code,
                greaterThanEqualDate = startDateOfWeek,
                lessThanDate = startDateOfNextWeek
            )

        // then
        assertThat(foundThisWeekDailyPrices.size).isEqualTo(thisWeekDataSize)
    }

    private fun getPricesBefore6DaysUntilToday(): List<DailyPrice> {
        val prices = emptyList<DailyPrice>().toMutableList()
        val today = LocalDate.now()

        for (i: Long in -6..0L) {
            val targetDate = today.plusDays(i)
            val dailyPrice = DailyPrice(
                DailyPriceId(
                    CompanyCode("000001"),
                    targetDate
                ),
                Price(1),
                Price(2),
                Price(3),
                Price(4)
            )
            prices.add(dailyPrice)
        }

        return prices
    }
}
