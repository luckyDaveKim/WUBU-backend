package com.wubu.api.common.exception

class InvalidPriceException(price: Long) : WubuException("가격은 음수일 수 없습니다. {price:'$price'}")
