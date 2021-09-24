package com.wubu.api.company.favorite.repository

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import com.wubu.api.company.favorite.entity.FavoriteCompany
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
class FavoriteCompanyRepositoryTest(
    @Autowired
    private val favoriteCompanyRepository: FavoriteCompanyRepository
) {

    private lateinit var userId: String
    private lateinit var company1: Company
    private lateinit var company2: Company
    private lateinit var company3: Company
    private lateinit var company4: Company
    private lateinit var favoriteCompany1: FavoriteCompany
    private lateinit var favoriteCompany2: FavoriteCompany
    private lateinit var favoriteCompany3: FavoriteCompany
    private lateinit var favoriteCompany4: FavoriteCompany

    @BeforeEach
    fun setUp() {
        userId = "dave"

        company1 = Company(
            id = CompanyId(CompanyCode("000001")),
            name = "company name1",
            date = LocalDate.of(1991, 3, 26)
        )
        company2 = Company(
            id = CompanyId(CompanyCode("000002")),
            name = "company name2",
            date = LocalDate.of(1991, 3, 26)
        )
        company3 = Company(
            id = CompanyId(CompanyCode("000003")),
            name = "company name3",
            date = LocalDate.of(1991, 3, 26)
        )
        company4 = Company(
            id = CompanyId(CompanyCode("000004")),
            name = "company name4",
            date = LocalDate.of(1991, 3, 26)
        )

        favoriteCompany1 = FavoriteCompany(
            company = company1
        )
        favoriteCompany2 = FavoriteCompany(
            company = company2
        )
        favoriteCompany3 = FavoriteCompany(
            company = company3
        )
        favoriteCompany4 = FavoriteCompany(
            company = company4
        )

        favoriteCompanyRepository.deleteAll()

        favoriteCompanyRepository.save(favoriteCompany1)
        favoriteCompanyRepository.save(favoriteCompany2)
        favoriteCompanyRepository.save(favoriteCompany3)
        favoriteCompanyRepository.save(favoriteCompany4)
    }

    @Test
    fun `id 기준 조회 테스트`() {
        // given

        // when
        val foundFavoriteCompany = favoriteCompanyRepository.findById(favoriteCompany1.id)

        // then
        assertThat(foundFavoriteCompany.get()).isEqualTo(favoriteCompany1)
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
        val foundFavoriteCompanies = favoriteCompanyRepository.findAllByOrderByCompany_NameAsc(
            pageable = pagingReqDto.getPageable()
        )

        // then
        assertThat(foundFavoriteCompanies).isNotNull
        assertThat(foundFavoriteCompanies.size).isEqualTo(2)
        assertThat(foundFavoriteCompanies[0]).isEqualTo(favoriteCompany1)
        assertThat(foundFavoriteCompanies[1]).isEqualTo(favoriteCompany2)
    }
}
