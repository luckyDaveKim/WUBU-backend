package com.wubu.api.application.company

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import com.wubu.api.domain.company.CompanyService
import com.wubu.api.interfaces.company.CompanyRes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class CompanyFacadeTest {

    @Mock
    private lateinit var companyService: CompanyService

    @InjectMocks
    private lateinit var companyFacade: CompanyFacade

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
        val companyResList = companies.map { company -> CompanyRes.of(company) }

        given(companyService.retrieveCompanies(pagingReqDto))
            .willReturn(companyResList)

        // when
        val foundCompanies = companyFacade.retrieveCompanies(pagingReqDto)

        // then
        assertThat(foundCompanies).isEqualTo(companyResList)
    }
}
