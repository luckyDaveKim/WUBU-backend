package com.wubu.api.company.favorite.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.company.favorite.dto.res.FavoriteCompaniesResDto
import com.wubu.api.company.favorite.service.FavoriteCompanyFindService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/companies/favorite")
class FavoriteCompanyController(
    private val favoriteCompanyFindService: FavoriteCompanyFindService
) {

    @GetMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findCompanies(
        @ModelAttribute pagingReqDto: PagingReqDto
    ): FavoriteCompaniesResDto {
        return favoriteCompanyFindService.findCompanies()
    }
}
