package com.wubu.api.interfaces.company.favorite

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.application.company.favorite.FavoriteCompanyFindService
import com.wubu.api.application.company.favorite.FavoriteCompanySaveService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.interfaces.company.CompanyRes
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(FavoriteCompanyController::class)
internal class FavoriteCompanyControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var favoriteCompanyFindService: FavoriteCompanyFindService

    @MockBean
    private lateinit var favoriteCompanySaveService: FavoriteCompanySaveService

    private val objectMapper = ObjectMapper()

    @Test
    fun `즐겨찾기 회사 리스트 조회 테스트`() {
        // given
        val page = 1
        val pageSize = 2
        val pagingReqDto = PagingReqDto(
            page = page,
            pageSize = pageSize
        )

        val companyRes1 = CompanyRes(
            code = CompanyCode("000001"),
            name = "company name1"
        )
        val companyRes2 = CompanyRes(
            code = CompanyCode("000002"),
            name = "company name2"
        )
        val favoriteCompanyResList = listOf(companyRes1, companyRes2)
        val jsonFavoriteCompaniesResDto = objectMapper.writeValueAsString(favoriteCompanyResList)

        given(
            favoriteCompanyFindService.findCompanies(
                pagingReqDto = pagingReqDto
            )
        )
            .willReturn(favoriteCompanyResList)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/companies/favorite")
                .param("page", page.toString())
                .param("pageSize", pageSize.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonFavoriteCompaniesResDto))
            .andDo { print() }
    }

    @Test
    fun `즐겨찾기 회사 리스트 저장 테스트`() {
        // given
        val reqCompanyCodes = HashMap<String, Any>()
        reqCompanyCodes["companyCodes"] = listOf("000001")
        val jsonReqCompanyCodes = objectMapper.writeValueAsString(reqCompanyCodes)

        val favoriteCompanyReq = FavoriteCompanyReq(listOf(CompanyCode("000001")))

        // when
        val resultActions: ResultActions = mockMvc.perform(
            put("/api/companies/favorite")
                .content(jsonReqCompanyCodes)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        verify(favoriteCompanySaveService)
            .saveCompanies(favoriteCompanyReq)
        resultActions.andExpect { status().isOk }
            .andDo { print() }
    }
}
