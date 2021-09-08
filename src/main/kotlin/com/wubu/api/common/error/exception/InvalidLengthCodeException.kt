package com.wubu.api.common.error.exception

class InvalidLengthCodeException(code: String) : WubuException("코드 길이는 6글자여야 합니다. {code:'${code}'}")