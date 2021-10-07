package com.wubu.api.interfaces.company

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.company.CompanyFindService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.domain.company.Company
import com.wubu.api.domain.company.CompanyId
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@WebMvcTest(CompanyController::class)
internal class CompanyControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var companyFindService: CompanyFindService

    private val objectMapper = ObjectMapper()

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
        val companiesResDto = CompaniesResDto.of(listOf(company1, company2))
        val jsonCompaniesResDto = objectMapper.writeValueAsString(companiesResDto)

        given(
            companyFindService.findCompanies(
                pagingReqDto = pagingReqDto
            )
        )
            .willReturn(companiesResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/companies")
                .param("page", page.toString())
                .param("pageSize", pageSize.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonCompaniesResDto))
            .andDo { print() }
    }

    @Test
    fun `default 페이징 정보 기반 회사 리스트 조회 테스트`() {
        // given
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
        val companiesResDto = CompaniesResDto.of(listOf(company1, company2))
        val jsonCompaniesResDto = objectMapper.writeValueAsString(companiesResDto)

        given(
            companyFindService.findCompanies(
                pagingReqDto = PagingReqDto()
            )
        )
            .willReturn(companiesResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonCompaniesResDto))
            .andDo { print() }
    }
}
