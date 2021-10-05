package com.wubu.api.application.exchangerate.usd.status

import com.wubu.api.common.exception.ResourceNotFoundException
import java.time.LocalDate

class UsdExchangeRateStatusNotFoundException(date: LocalDate) :
    ResourceNotFoundException("환율 상태정보가 없습니다. {date:'$date'}")
