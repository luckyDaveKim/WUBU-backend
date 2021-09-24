package com.wubu.api.exchangerate.status.usd.exception

import com.wubu.api.common.error.exception.ResourceNotFoundException
import java.time.LocalDate

class UsdExchangeRateStatusNotFoundException(date: LocalDate) :
    ResourceNotFoundException("환율 상태정보가 없습니다. {date:'$date'}")
