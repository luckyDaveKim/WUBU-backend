package com.wubu.api.common.exception

class InvalidRateException(rate: Double) : WubuException("환율은 음수일 수 없습니다. {rate:'$rate'}")
