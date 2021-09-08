package com.wubu.api.stockvalue.daily.repository

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.common.web.model.stockvalue.Price
import com.wubu.api.common.web.model.stockvalue.Volume
import com.wubu.api.stockvalue.daily.entity.DailyPrice
import com.wubu.api.stockvalue.daily.entity.DailyPriceId
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
class DailyPriceRepositoryTest {
    @Autowired
    lateinit var dailyPriceRepository: DailyPriceRepository

    lateinit var dailyPrice1: DailyPrice
    lateinit var dailyPrice2: DailyPrice
    lateinit var dailyPrice3: DailyPrice

    @BeforeEach
    fun setUp() {
        dailyPrice1 = DailyPrice(
                DailyPriceId(
                        Code("000001"),
                        LocalDate.of(1991, 3, 26)),
                Price(1),
                Price(2),
                Price(3),
                Price(4),
                5,
                Volume(6))
        dailyPrice2 = DailyPrice(
                DailyPriceId(
                        Code("000001"),
                        LocalDate.of(1991, 3, 28)),
                Price(10),
                Price(20),
                Price(30),
                Price(40),
                50,
                Volume(60))
        dailyPrice3 = DailyPrice(
                DailyPriceId(
                        Code("000001"),
                        LocalDate.of(1991, 3, 27)),
                Price(100),
                Price(200),
                Price(300),
                Price(400),
                500,
                Volume(600))

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
    fun `code 기준 리스트 조회 테스트`() {
        // given
        val code = dailyPrice1.id.code
        val pageable = PagingReqDto().getPageable()

        // when
        val foundDailPrices = dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(code, pageable)

        // then
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

    @Test
    fun `페이징 테스트`() {
        // given
        val code = dailyPrice1.id.code
        val page = 2
        val pageSize = 1
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailPrices = dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(code, pageable)

        // then
        assertThat(foundDailPrices.size).isEqualTo(pageSize)

        assertThat(foundDailPrices[0].id.code).isEqualTo(dailyPrice3.id.code)
        assertThat(foundDailPrices[0].id.date).isEqualTo(dailyPrice3.id.date)
        assertThat(foundDailPrices[0].open).isEqualTo(dailyPrice3.open)
        assertThat(foundDailPrices[0].high).isEqualTo(dailyPrice3.high)
        assertThat(foundDailPrices[0].low).isEqualTo(dailyPrice3.low)
        assertThat(foundDailPrices[0].close).isEqualTo(dailyPrice3.close)
        assertThat(foundDailPrices[0].diff).isEqualTo(dailyPrice3.diff)
        assertThat(foundDailPrices[0].volume).isEqualTo(dailyPrice3.volume)
    }

    @Test
    fun `페이징 사이즈 테스트`() {
        // given
        val code = dailyPrice1.id.code
        val page = 1
        val pageSize = 2
        val pageable = PagingReqDto(page, pageSize).getPageable()

        // when
        val foundDailPrices = dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(code, pageable)

        // then
        assertThat(foundDailPrices.size).isEqualTo(pageSize)

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
    }
}