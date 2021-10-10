package com.wubu.api.interfaces.company.favorite

import com.wubu.api.application.company.favorite.FavoriteCompanyFindService
import com.wubu.api.application.company.favorite.FavoriteCompanySaveService
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.interfaces.company.CompanyRes
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
    ): List<CompanyRes> {
        return favoriteCompanyFindService.findCompanies(pagingReqDto)
    }

    @PutMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun saveCompanies(
        @RequestBody @Valid favoriteCompanyReq: FavoriteCompanyReq
    ) {
        favoriteCompanySaveService.saveCompanies(favoriteCompanyReq)
    }
}
