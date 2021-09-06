package com.wubu.api.price.daily.model

import com.wubu.api.price.daily.exception.InvalidPriceException
import com.wubu.api.price.daily.model.Price.PriceConverter
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PriceTest {

    @Test
    fun `생성 테스트`() {
        val priceValue = 123456L
        val price = Price(priceValue)

        assertThat(price.value).isEqualTo(priceValue)
    }

    @Test
    fun `가격이 음수인 오류 테스트`() {
        Assertions.assertThatThrownBy { Price(-1) }
                .isInstanceOf(InvalidPriceException::class.java)
    }

    @Test
    fun `동등성 비교 테스트`() {
        val priceValue = 1L
        val price1 = Price(priceValue)
        val price2 = Price(priceValue)

        assertThat(price1).isEqualTo(price2)
        assertThat(price1.value).isEqualTo(priceValue)
        assertThat(price2.value).isEqualTo(priceValue)
    }

    @Test
    fun `DB to Object 테스트`() {
        val dbPriceValue = 100L
        val price = PriceConverter().convertToEntityAttribute(dbPriceValue)

        assertThat(price).isNotNull
        assertThat(price.value).isEqualTo(dbPriceValue)
    }

    @Test
    fun `DB to Object 0 값 테스트`() {
        val dbPriceValue = 0L
        val price = PriceConverter().convertToEntityAttribute(dbPriceValue)

        assertThat(price).isNotNull
        assertThat(price.value).isEqualTo(dbPriceValue)
    }

    @Test
    fun `DB to Object Null 값 테스트`() {
        val dbPriceValue = null
        val price = PriceConverter().convertToEntityAttribute(dbPriceValue)

        assertThat(price).isNotNull
        assertThat(price.value).isEqualTo(0)
    }

    @Test
    fun `Object to DB 테스트`() {
        val priceValue = 100L
        val price = Price(priceValue)
        val dbPrice = PriceConverter().convertToDatabaseColumn(price)

        assertThat(dbPrice).isEqualTo(priceValue)
    }

    @Test
    fun `Object to DB 0 값 테스트`() {
        val priceValue = 0L
        val price = Price(priceValue)
        val dbPrice = PriceConverter().convertToDatabaseColumn(price)

        assertThat(dbPrice).isEqualTo(priceValue)
    }

    @Test
    fun `Object to DB Null 값 테스트`() {
        val price = null
        val dbPrice = PriceConverter().convertToDatabaseColumn(price)

        assertThat(dbPrice).isEqualTo(0)
    }

}