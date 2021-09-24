package com.wubu.api.company.repository

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import com.wubu.api.company.favorite.repository.FavoriteCompanyRepository
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
class CompanyRepositoryTest(
    @Autowired
    private val companyRepository: CompanyRepository,
    @Autowired
    private val favoriteCompanyRepository: FavoriteCompanyRepository
) {

    private lateinit var company1: Company
    private lateinit var company2: Company
    private lateinit var company3: Company
    private lateinit var company4: Company

    @BeforeEach
    fun setUp() {
        favoriteCompanyRepository.deleteAll()
        companyRepository.deleteAll()

        company1 = Company(
            id = CompanyId(CompanyCode("000001")),
            name = "company name1",
            date = LocalDate.of(1991, 3, 26)
        )
        company2 = Company(
            id = CompanyId(CompanyCode("000002")),
            name = "company name2",
            date = LocalDate.of(1991, 3, 27)
        )
        company3 = Company(
            id = CompanyId(CompanyCode("000003")),
            name = "company name3",
            date = LocalDate.of(1991, 3, 28)
        )
        company4 = Company(
            id = CompanyId(CompanyCode("000004")),
            name = "company name4",
            date = LocalDate.of(1991, 3, 29)
        )

        companyRepository.save(company4)
        companyRepository.save(company3)
        companyRepository.save(company2)
        companyRepository.save(company1)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given
        val code = company1.id

        // when
        val foundCompany = companyRepository.findById(code)

        // then
        assertThat(foundCompany.get()).isEqualTo(company1)
    }

    @Test
    fun `전체 조회 테스트`() {
        // given
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

        // when
        val foundCompanies = companyRepository.findAllByOrderByNameAsc(
            pageable = pagingReqDto.getPageable()
        )

        // then
        assertThat(foundCompanies).isNotNull
        assertThat(foundCompanies.size).isEqualTo(pageSize)
        assertThat(foundCompanies[0]).isEqualTo(company1)
        assertThat(foundCompanies[1]).isEqualTo(company2)
    }
}
