package com.wubu.api.interfaces.company.favorite

import com.wubu.api.application.company.favorite.FavoriteCompanyFacade
import com.wubu.api.common.web.dto.PagingReqDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/companies/favorite")
class FavoriteCompanyController(
    private val favoriteCompanyFacade: FavoriteCompanyFacade,
) {

    @GetMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findCompanies(
        @ModelAttribute pagingReqDto: PagingReqDto
    ): List<FavoriteCompanyRes> {
        return favoriteCompanyFacade.retrieveFavoriteCompanies(pagingReqDto)
    }

    @PutMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun saveCompanies(
        @RequestBody @Valid favoriteCompanyReq: FavoriteCompanyReq
    ) {
        favoriteCompanyFacade.registerFavoriteCompanies(favoriteCompanyReq)
    }
}
