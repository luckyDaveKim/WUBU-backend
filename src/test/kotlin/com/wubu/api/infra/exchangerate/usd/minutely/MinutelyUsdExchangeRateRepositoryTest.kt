package com.wubu.api.infra.exchangerate.usd.minutely

import com.wubu.api.common.web.model.exchangerate.Rate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRate
import com.wubu.api.domain.exchangerate.usd.minutely.MinutelyUsdExchangeRateId
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
class MinutelyUsdExchangeRateRepositoryTest(
    @Autowired
    private val minutelyUsdExchangeRateRepository: MinutelyUsdExchangeRateRepository
) {

    private lateinit var minutelyUsdExchangeRate1: MinutelyUsdExchangeRate
    private lateinit var minutelyUsdExchangeRate2: MinutelyUsdExchangeRate
    private lateinit var minutelyUsdExchangeRate3: MinutelyUsdExchangeRate
    private lateinit var minutelyUsdExchangeRate4: MinutelyUsdExchangeRate
    private lateinit var minutelyUsdExchangeRate5: MinutelyUsdExchangeRate

    @BeforeEach
    fun setUp() {
        minutelyUsdExchangeRateRepository.deleteAll()

        minutelyUsdExchangeRate1 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 25, 9, 0, 0),
                rate = Rate(1.0)
            )
        )
        minutelyUsdExchangeRate2 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 9, 0, 0),
                rate = Rate(2.0)
            )
        )
        minutelyUsdExchangeRate3 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 10, 0, 0),
                rate = Rate(3.0)
            )
        )
        minutelyUsdExchangeRate4 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 26, 11, 0, 0),
                rate = Rate(4.0)
            )
        )
        minutelyUsdExchangeRate5 = MinutelyUsdExchangeRate(
            id = MinutelyUsdExchangeRateId(
                dateTime = LocalDateTime.of(1991, 3, 27, 9, 0, 0),
                rate = Rate(5.0)
            )
        )

        minutelyUsdExchangeRateRepository.save(minutelyUsdExchangeRate1)
        minutelyUsdExchangeRateRepository.save(minutelyUsdExchangeRate2)
        minutelyUsdExchangeRateRepository.save(minutelyUsdExchangeRate3)
        minutelyUsdExchangeRateRepository.save(minutelyUsdExchangeRate4)
        minutelyUsdExchangeRateRepository.save(minutelyUsdExchangeRate5)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given

        // when
        val foundMinutelyUsdExchangeRate = minutelyUsdExchangeRateRepository.findById(minutelyUsdExchangeRate1.id)

        // then
        assertThat(foundMinutelyUsdExchangeRate.get()).isEqualTo(minutelyUsdExchangeRate1)
    }

    @Test
    fun `날짜 기준 Top 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        // when
        val foundMinutelyUsdExchangeRates =
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )

        // then
        assertThat(foundMinutelyUsdExchangeRates).isEqualTo(minutelyUsdExchangeRate4)
    }

    @Test
    fun `미존재 데이터 날짜 기준 Top 조회 테스트`() {
        // given
        val date = LocalDate.of(1981, 3, 26)
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        // when
        val foundMinutelyUsdExchangeRates =
            minutelyUsdExchangeRateRepository.findTopById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )

        // then
        assertThat(foundMinutelyUsdExchangeRates).isNull()
    }

    @Test
    fun `날짜 기준 조회 테스트`() {
        // given
        val date = LocalDate.of(1991, 3, 26)
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        // when
        val foundMinutelyUsdExchangeRates =
            minutelyUsdExchangeRateRepository.findAllById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )

        // then
        assertThat(foundMinutelyUsdExchangeRates).isNotNull
        assertThat(foundMinutelyUsdExchangeRates.size).isEqualTo(3)
        assertThat(foundMinutelyUsdExchangeRates[0]).isEqualTo(minutelyUsdExchangeRate4)
        assertThat(foundMinutelyUsdExchangeRates[1]).isEqualTo(minutelyUsdExchangeRate3)
        assertThat(foundMinutelyUsdExchangeRates[2]).isEqualTo(minutelyUsdExchangeRate2)
    }

    @Test
    fun `미존재 데이터 날짜 기준 조회 테스트`() {
        // given
        val date = LocalDate.of(1981, 3, 26)
        val afterEqualDateTime = date.atStartOfDay()
        val beforeDateTime = date.plusDays(1).atStartOfDay()

        // when
        val foundMinutelyUsdExchangeRates =
            minutelyUsdExchangeRateRepository.findAllById_DateTimeGreaterThanEqualAndId_DateTimeLessThanOrderById_DateTimeDesc(
                afterEqualDateTime = afterEqualDateTime,
                beforeDateTime = beforeDateTime
            )

        // then
        assertThat(foundMinutelyUsdExchangeRates).isNotNull
        assertThat(foundMinutelyUsdExchangeRates.size).isEqualTo(0)
    }
}
