package com.wubu.api.infra.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class CompanyReaderImplTest {

    @Mock
    private lateinit var companyRepository: CompanyRepository

    @InjectMocks
    private lateinit var companyReader: CompanyReaderImpl

    private lateinit var company1: Company
    private lateinit var company2: Company

    @BeforeEach
    fun setUp() {
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
    }

    @Test
    fun `코드 리스트 기준 회사 리스트 조회 테스트`() {
        // given
        val companyIds = listOf(company1.id, company2.id)
        val companies = listOf(company1, company2)

        given(companyRepository.findAllByIdIn(companyIds))
            .willReturn(companies)

        // when
        val companyCodes = companyIds.map { companyId -> companyId.companyCode }
        val foundCompanies = companyReader.getCompaniesByCodes(companyCodes)

        // then
        assertThat(foundCompanies).isEqualTo(companies)
    }

    @Test
    fun `회사 리스트 조회 테스트`() {
        // given
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

        val companies = listOf(company1, company2)

        given(companyRepository.findAllByOrderByNameAsc(pagingReqDto.getPageable()))
            .willReturn(companies)

        // when
        val foundCompanies = companyReader.getCompanies(pagingReqDto)

        // then
        assertThat(foundCompanies).isEqualTo(companies)
    }
}
