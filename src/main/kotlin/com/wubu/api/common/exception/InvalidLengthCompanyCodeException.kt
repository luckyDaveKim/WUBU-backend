package com.wubu.api.common.exception

class InvalidLengthCompanyCodeException(companyCode: String) :
    WubuException("코드 길이는 6글자여야 합니다. {companyCode:'$companyCode'}")
