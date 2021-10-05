package com.wubu.api.common.exception

class InvalidVolumeException(volume: Long) : WubuException("거래량은 음수일 수 없습니다. {volume:'$volume'}")
