package com.wubu.api.company.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.company.dto.res.CompaniesResDto
import com.wubu.api.company.service.CompanyFindService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/companies")
class CompanyController(
        private val companyFindService: CompanyFindService
) {

    @GetMapping(
            "",
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findCompanies(
            @ModelAttribute pagingReqDto: PagingReqDto): CompaniesResDto {
        return companyFindService.findCompanies()
    }

}