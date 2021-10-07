package com.wubu.api.infra.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.daily.DailyUsdExchangeRateId
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
internal class DailyUsdExchangeRateRepositoryTest(
    @Autowired
    private val dailyUsdExchangeRateRepository: DailyUsdExchangeRateRepository
) {

    private lateinit var dailyUsdExchangeRate1: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate2: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate3: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate4: DailyUsdExchangeRate
    private lateinit var dailyUsdExchangeRate5: DailyUsdExchangeRate

    @BeforeEach
    fun setUp() {
        dailyUsdExchangeRateRepository.deleteAll()

        dailyUsdExchangeRate1 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 24),
                rate = Rate(1.1)
            )
        )
        dailyUsdExchangeRate2 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 25),
                rate = Rate(2.2)
            )
        )
        dailyUsdExchangeRate3 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 26),
                rate = Rate(3.3)
            )
        )
        dailyUsdExchangeRate4 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 27),
                rate = Rate(4.4)
            )
        )
        dailyUsdExchangeRate5 = DailyUsdExchangeRate(
            id = DailyUsdExchangeRateId(
                date = LocalDate.of(1991, 3, 28),
                rate = Rate(5.5)
            )
        )

        dailyUsdExchangeRateRepository.save(dailyUsdExchangeRate1)
        dailyUsdExchangeRateRepository.save(dailyUsdExchangeRate2)
        dailyUsdExchangeRateRepository.save(dailyUsdExchangeRate3)
        dailyUsdExchangeRateRepository.save(dailyUsdExchangeRate4)
        dailyUsdExchangeRateRepository.save(dailyUsdExchangeRate5)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given
        val id = dailyUsdExchangeRate1.id

        // when
        val foundDailyUsdExchangeRate = dailyUsdExchangeRateRepository.findById(id)

        // then
        assertThat(foundDailyUsdExchangeRate.get()).isEqualTo(dailyUsdExchangeRate1)
    }

    @Test
    fun `페이징1 사이즈3 테스트`() {
        // given
        val page = 1
        val pageSize = 3
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailyUsdExchangeRates = dailyUsdExchangeRateRepository.findAllByOrderById_DateDesc(pageable)

        // then
        assertThat(foundDailyUsdExchangeRates.size).isEqualTo(pageSize)
        assertThat(foundDailyUsdExchangeRates[0]).isEqualTo(dailyUsdExchangeRate5)
        assertThat(foundDailyUsdExchangeRates[1]).isEqualTo(dailyUsdExchangeRate4)
        assertThat(foundDailyUsdExchangeRates[2]).isEqualTo(dailyUsdExchangeRate3)
    }

    @Test
    fun `페이징2 사이즈2 테스트`() {
        // given
        val page = 2
        val pageSize = 2
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailyUsdExchangeRates = dailyUsdExchangeRateRepository.findAllByOrderById_DateDesc(pageable)

        // then
        assertThat(foundDailyUsdExchangeRates.size).isEqualTo(pageSize)
        assertThat(foundDailyUsdExchangeRates[0]).isEqualTo(dailyUsdExchangeRate3)
        assertThat(foundDailyUsdExchangeRates[1]).isEqualTo(dailyUsdExchangeRate2)
    }
}
