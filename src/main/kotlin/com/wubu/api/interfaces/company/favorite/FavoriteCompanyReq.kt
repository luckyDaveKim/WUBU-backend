package com.wubu.api.interfaces.company.favorite

import com.wubu.api.common.web.model.CompanyCode

data class FavoriteCompanyReq(
    val companyCodes: List<CompanyCode>
)
