package com.wubu.api.company.entity

import com.wubu.api.common.web.model.CompanyCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CompanyTest {

    private lateinit var id1: CompanyId
    private lateinit var id2: CompanyId
    private lateinit var name1: String
    private lateinit var name2: String
    private lateinit var date1: LocalDate
    private lateinit var date2: LocalDate

    @BeforeEach
    fun setUp() {
        id1 = CompanyId(CompanyCode("000000"))
        id2 = CompanyId(CompanyCode("000001"))
        name1 = "company name1"
        name2 = "company name2"
        date1 = LocalDate.of(1991, 3, 26)
        date2 = LocalDate.of(1991, 3, 27)
    }

    @Test
    fun `생정 테스트`() {
        // given

        // when
        val company = Company(
            id1,
            name1,
            date1
        )

        // then
        assertThat(company.id).isEqualTo(id1)
        assertThat(company.name).isEqualTo(name1)
        assertThat(company.date).isEqualTo(date1)
    }

    @Test
    fun `동등성 비교 테스트`() {
        // given

        // when
        val company1 = Company(
            id1,
            name1,
            date1
        )
        val company2 = Company(
            id1,
            name1,
            date1
        )

        // then
        assertThat(company1).isEqualTo(company2)
    }

    @Test
    fun `동등성 비교 실패 테스트`() {
        // given

        // when
        val company1 = Company(
            id1,
            name1,
            date1
        )
        val company2 = Company(
            id2,
            name2,
            date2
        )

        // then
        assertThat(company1).isNotEqualTo(company2)
    }

    @Test
    fun `hashCode 비교 테스트`() {
        // given

        // when
        val company1 = Company(
            id1,
            name1,
            date1
        )
        val company2 = Company(
            id1,
            name1,
            date1
        )

        // then
        assertThat(company1.hashCode()).isEqualTo(company2.hashCode())
    }

    @Test
    fun `hashCode 비교 실패 테스트`() {
        // given

        // when
        val company1 = Company(
            id1,
            name1,
            date1
        )
        val company2 = Company(
            id2,
            name2,
            date2
        )

        // then
        assertThat(company1.hashCode()).isNotEqualTo(company2.hashCode())
    }
}
