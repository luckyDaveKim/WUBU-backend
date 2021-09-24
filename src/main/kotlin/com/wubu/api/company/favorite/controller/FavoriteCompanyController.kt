package com.wubu.api.company.favorite.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.company.favorite.dto.req.FavoriteCompanyReqDto
import com.wubu.api.company.favorite.dto.res.FavoriteCompaniesResDto
import com.wubu.api.company.favorite.service.FavoriteCompanyFindService
import com.wubu.api.company.favorite.service.FavoriteCompanySaveService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/companies/favorite")
class FavoriteCompanyController(
    private val favoriteCompanyFindService: FavoriteCompanyFindService,
    private val favoriteCompanySaveService: FavoriteCompanySaveService
) {

    @GetMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findCompanies(
        @ModelAttribute pagingReqDto: PagingReqDto
    ): FavoriteCompaniesResDto {
        return favoriteCompanyFindService.findCompanies(pagingReqDto)
    }

    @PutMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun saveCompanies(
        @RequestBody favoriteCompanyReqDto: FavoriteCompanyReqDto
    ) {
        favoriteCompanySaveService.saveCompanies(favoriteCompanyReqDto)
    }
}
