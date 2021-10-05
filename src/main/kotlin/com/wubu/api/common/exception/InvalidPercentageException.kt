package com.wubu.api.common.exception

class InvalidPercentageException(percentage: Double) : WubuException("백분율은 음수일 수 없습니다. {percentage:'$percentage'}")
