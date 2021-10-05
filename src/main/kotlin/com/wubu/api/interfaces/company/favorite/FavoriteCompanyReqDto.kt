package com.wubu.api.interfaces.company.favorite

import com.wubu.api.common.web.model.CompanyCode

data class FavoriteCompanyReqDto(
    val companyCodes: List<CompanyCode>
)
