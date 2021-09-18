package com.wubu.api.company.favorite.dto.req

import com.wubu.api.common.web.model.CompanyCode

data class FavoriteCompanyReqDto(
    val companyCodes: List<CompanyCode>
)
