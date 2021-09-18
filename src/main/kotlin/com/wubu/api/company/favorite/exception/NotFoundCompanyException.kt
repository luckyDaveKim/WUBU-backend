package com.wubu.api.company.favorite.exception

import com.wubu.api.common.error.exception.WubuException
import com.wubu.api.common.web.model.CompanyCode

class NotFoundCompanyException(companyCode: CompanyCode) :
    WubuException("회사 정보를 찾을 수 없습니다. {companyCode:'$companyCode'}")
