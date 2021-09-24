package com.wubu.api.company.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.company.dto.res.CompaniesResDto
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import com.wubu.api.company.repository.CompanyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class CompanyFindServiceTest {

    @Mock
    private lateinit var companyRepository: CompanyRepository

    @InjectMocks
    private lateinit var companyFindService: CompanyFindService

    @Test
    fun `회사 리스트 조회 테스트`() {
        // given
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

        val company1 = Company(
            id = CompanyId(CompanyCode("000001")),
            name = "company name1",
            date = LocalDate.of(1991, 3, 26)
        )
        val company2 = Company(
            id = CompanyId(CompanyCode("000002")),
            name = "company name2",
            date = LocalDate.of(1991, 3, 26)
        )
        val companies = listOf(company1, company2)
        val companiesResDto = CompaniesResDto.of(companies)

        given(
            companyRepository.findAllByOrderByNameAsc(
                pageable = pagingReqDto.getPageable()
            )
        )
            .willReturn(companies)

        // when
        val foundCompanies = companyFindService.findCompanies(pagingReqDto)

        // then
        assertThat(foundCompanies).isEqualTo(companiesResDto)
    }
}
