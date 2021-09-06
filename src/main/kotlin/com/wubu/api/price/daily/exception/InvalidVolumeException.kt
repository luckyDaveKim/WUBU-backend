package com.wubu.api.price.daily.exception

import com.wubu.api.common.error.exception.WubuException

class InvalidVolumeException(volume: Long) : WubuException("거래량은 음수일 수 없습니다. {volume:'${volume}'}")