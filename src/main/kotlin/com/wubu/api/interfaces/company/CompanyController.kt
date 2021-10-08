package com.wubu.api.interfaces.company

import com.wubu.api.application.company.CompanyFindService
import com.wubu.api.common.web.dto.PagingReqDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/companies")
class CompanyController(
    private val companyFindService: CompanyFindService
) {

    @GetMapping(
        "",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findCompanies(
        @ModelAttribute pagingReqDto: PagingReqDto
    ): List<CompanyRes> {
        return companyFindService.findCompanies(pagingReqDto)
    }
}
