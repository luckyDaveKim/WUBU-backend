package com.wubu.api.favorite.company.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.favorite.company.dto.FavoriteCompanyDto
import com.wubu.api.favorite.company.dto.res.FavoriteCompaniesResDto
import com.wubu.api.favorite.company.service.FavoriteCompanyFindService
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

@WebMvcTest(FavoriteCompanyController::class)
class FavoriteCompanyControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var favoriteCompanyFindService: FavoriteCompanyFindService

    private val objectMapper = ObjectMapper()

    @Test
    fun `즐겨찾기 회사 리스트 조회 테스트`() {
        // given
        val favoriteCompanyDto1 = FavoriteCompanyDto(CompanyCode("000001"))
        val favoriteCompanyDto2 = FavoriteCompanyDto(CompanyCode("000002"))
        val favoriteCompaniesResDto = FavoriteCompaniesResDto(setOf(favoriteCompanyDto1, favoriteCompanyDto2))
        val jsonFavoriteCompaniesResDto = objectMapper.writeValueAsString(favoriteCompaniesResDto)

        given(favoriteCompanyFindService.findCompanies())
            .willReturn(favoriteCompaniesResDto)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/companies/favorite")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        // then
        resultActions.andExpect { status().isOk }
            .andExpect(content().json(jsonFavoriteCompaniesResDto))
            .andDo { print() }
    }
}
