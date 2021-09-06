package com.wubu.api.price.daily.repository

import com.wubu.api.price.daily.entity.DailyPrice
import com.wubu.api.price.daily.entity.DailyPriceId
import com.wubu.api.price.daily.model.Code
import com.wubu.api.price.daily.model.Price
import com.wubu.api.price.daily.model.Volume
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ContextConfiguration(initializers = [ConfigDataApplicationContextInitializer::class])
@ActiveProfiles("test")
class DailyPriceRepositoryTest {
    @Autowired
    lateinit var dailyPriceRepository: DailyPriceRepository

    lateinit var dailyPrice1: DailyPrice
    lateinit var dailyPrice2: DailyPrice
    lateinit var dailyPrice3: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(DailyPriceId(Code("000001"), LocalDate.of(1991, 3, 26)), Price(1), Price(2), Price(3), Price(4), 5, Volume(6))
        dailyPrice2 = DailyPrice(DailyPriceId(Code("000001"), LocalDate.of(1991, 3, 28)), Price(10), Price(20), Price(30), Price(40), 50, Volume(60))
        dailyPrice3 = DailyPrice(DailyPriceId(Code("000001"), LocalDate.of(1991, 3, 27)), Price(100), Price(200), Price(300), Price(400), 500, Volume(600))
    }

    @Test
    fun `일별 데이터 추가 및 조회`() {
        dailyPriceRepository.deleteAll()
        assertThat(dailyPriceRepository.count()).isEqualTo(0)

        dailyPriceRepository.save(dailyPrice1)
        assertThat(dailyPriceRepository.count()).isEqualTo(1)

        val foundDailPrice = dailyPriceRepository.findById(dailyPrice1.id)
        assertThat(foundDailPrice.get().id.code).isEqualTo(dailyPrice1.id.code)
        assertThat(foundDailPrice.get().id.date).isEqualTo(dailyPrice1.id.date)
        assertThat(foundDailPrice.get().open).isEqualTo(dailyPrice1.open)
        assertThat(foundDailPrice.get().high).isEqualTo(dailyPrice1.high)
        assertThat(foundDailPrice.get().low).isEqualTo(dailyPrice1.low)
        assertThat(foundDailPrice.get().close).isEqualTo(dailyPrice1.close)
        assertThat(foundDailPrice.get().diff).isEqualTo(dailyPrice1.diff)
        assertThat(foundDailPrice.get().volume).isEqualTo(dailyPrice1.volume)
    }

    @Test
    fun `일별 다중 데이터 추가 및 조회`() {
        dailyPriceRepository.deleteAll()
        assertThat(dailyPriceRepository.count()).isEqualTo(0)

        dailyPriceRepository.save(dailyPrice1)
        dailyPriceRepository.save(dailyPrice2)
        dailyPriceRepository.save(dailyPrice3)
        assertThat(dailyPriceRepository.count()).isEqualTo(3)

        val foundDailPrices = dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(dailyPrice1.id.code)
        assertThat(foundDailPrices[0].id.code).isEqualTo(dailyPrice1.id.code)
        assertThat(foundDailPrices[0].id.date).isEqualTo(dailyPrice1.id.date)
        assertThat(foundDailPrices[0].open).isEqualTo(dailyPrice1.open)
        assertThat(foundDailPrices[0].high).isEqualTo(dailyPrice1.high)
        assertThat(foundDailPrices[0].low).isEqualTo(dailyPrice1.low)
        assertThat(foundDailPrices[0].close).isEqualTo(dailyPrice1.close)
        assertThat(foundDailPrices[0].diff).isEqualTo(dailyPrice1.diff)
        assertThat(foundDailPrices[0].volume).isEqualTo(dailyPrice1.volume)

        assertThat(foundDailPrices[1].id.code).isEqualTo(dailyPrice3.id.code)
        assertThat(foundDailPrices[1].id.date).isEqualTo(dailyPrice3.id.date)
        assertThat(foundDailPrices[1].open).isEqualTo(dailyPrice3.open)
        assertThat(foundDailPrices[1].high).isEqualTo(dailyPrice3.high)
        assertThat(foundDailPrices[1].low).isEqualTo(dailyPrice3.low)
        assertThat(foundDailPrices[1].close).isEqualTo(dailyPrice3.close)
        assertThat(foundDailPrices[1].diff).isEqualTo(dailyPrice3.diff)
        assertThat(foundDailPrices[1].volume).isEqualTo(dailyPrice3.volume)

        assertThat(foundDailPrices[2].id.code).isEqualTo(dailyPrice2.id.code)
        assertThat(foundDailPrices[2].id.date).isEqualTo(dailyPrice2.id.date)
        assertThat(foundDailPrices[2].open).isEqualTo(dailyPrice2.open)
        assertThat(foundDailPrices[2].high).isEqualTo(dailyPrice2.high)
        assertThat(foundDailPrices[2].low).isEqualTo(dailyPrice2.low)
        assertThat(foundDailPrices[2].close).isEqualTo(dailyPrice2.close)
        assertThat(foundDailPrices[2].diff).isEqualTo(dailyPrice2.diff)
        assertThat(foundDailPrices[2].volume).isEqualTo(dailyPrice2.volume)
    }
}