package com.wubu.api.price.daily.exception

import com.wubu.api.common.error.exception.WubuException

class InvalidPriceException(price: Long) : WubuException("가격은 음수일 수 없습니다. {price:'${price}'}")