package com.wubu.api.common.web.model.stockvalue

import com.wubu.api.common.exception.InvalidPriceException
import com.wubu.api.common.web.model.SingleValue
import com.wubu.api.common.web.model.stockvalue.Price.PriceConverter
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class PriceTest {

    @Test
    fun `생성 테스트`() {
        // given
        val priceValue = 123456L

        // when
        val price = Price(priceValue)

        // then
        assertThat(price.value).isEqualTo(priceValue)
    }

    @Test
    fun `가격이 음수인 오류 테스트`() {
        // given
        val priceValue = -1L

        // when

        // then
        assertThatThrownBy { Price(priceValue) }
            .isInstanceOf(InvalidPriceException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given
        val priceValue = 1L

        // when
        val price1 = Price(priceValue)
        val price2 = Price(priceValue)

        // then
        assertThat(price1).isEqualTo(price2)
        assertThat(price1.value).isEqualTo(priceValue)
        assertThat(price2.value).isEqualTo(priceValue)
    }

    @Test
    fun `SingleValue 인터페이스 테스트`() {
        // given
        val priceValue = 123456L

        // when
        val singleValue: SingleValue<Long> = Price(priceValue)

        // then
        assertThat(singleValue.value).isEqualTo(priceValue)
    }

    @Test
    fun `StockValue 인터페이스 테스트`() {
        // given
        val priceValue = 123456L

        // when
        val stockValue: StockValue = Price(priceValue)

        // then
        assertThat(stockValue).isNotNull
    }

    @Test
    fun `DB to Object 테스트`() {
        // given
        val dbPriceValue = 100L

        // when
        val price = PriceConverter().convertToEntityAttribute(dbPriceValue)

        // then
        assertThat(price.value).isEqualTo(dbPriceValue)
    }

    @Test
    fun `DB to Object 0 값 테스트`() {
        // given
        val dbPriceValue = 0L

        // when
        val price = PriceConverter().convertToEntityAttribute(dbPriceValue)

        // then
        assertThat(price.value).isEqualTo(dbPriceValue)
    }

    @Test
    fun `DB to Object Null 값 테스트`() {
        // given
        val dbPriceValue = null

        // when
        val price = PriceConverter().convertToEntityAttribute(dbPriceValue)

        // then
        assertThat(price.value).isEqualTo(0)
    }

    @Test
    fun `Object to DB 테스트`() {
        // given
        val priceValue = 100L
        val price = Price(priceValue)

        // when
        val dbPrice = PriceConverter().convertToDatabaseColumn(price)

        // then
        assertThat(dbPrice).isEqualTo(priceValue)
    }

    @Test
    fun `Object to DB 0 값 테스트`() {
        // given
        val priceValue = 0L
        val price = Price(priceValue)

        // when
        val dbPrice = PriceConverter().convertToDatabaseColumn(price)

        // then
        assertThat(dbPrice).isEqualTo(priceValue)
    }

    @Test
    fun `Object to DB Null 값 테스트`() {
        // given
        val price = null

        // when
        val dbPrice = PriceConverter().convertToDatabaseColumn(price)

        // then
        assertThat(dbPrice).isEqualTo(0)
    }
}
