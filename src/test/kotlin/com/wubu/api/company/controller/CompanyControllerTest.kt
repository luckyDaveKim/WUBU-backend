package com.wubu.api.company.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.model.Code
import com.wubu.api.company.dto.res.CompaniesResDto
import com.wubu.api.company.entity.Company
import com.wubu.api.company.entity.CompanyId
import com.wubu.api.company.service.CompanyFindService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@WebMvcTest(CompanyController::class)
class CompanyControllerTest(
        @Autowired
        private val mockMvc: MockMvc
) {

    @MockBean
    lateinit var companyFindService: CompanyFindService

    private val objectMapper = ObjectMapper()

    @Test
    fun `회사 리스트 조회 테스트`() {
        // given
        val company1 = Company(
                id = CompanyId(Code("000001")),
                name = "company name1",
                date = LocalDate.of(1991, 3, 26))
        val company2 = Company(
                id = CompanyId(Code("000002")),
                name = "company name2",
                date = LocalDate.of(1991, 3, 26))
        val companiesResDto = CompaniesResDto.of(listOf(company1, company2))
        val jsonCompaniesResDto = objectMapper.writeValueAsString(companiesResDto)

        given(companyFindService.findCompanies())
                .willReturn(companiesResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

        // then
        resultActions.andExpect { status().isOk }
                .andExpect(content().json(jsonCompaniesResDto))
                .andDo { print() }
    }

}