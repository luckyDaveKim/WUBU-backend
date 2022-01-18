package com.wubu.api.domain.stock

import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.common.web.model.stockvalue.Volume
import java.time.Instant

interface Stock {
    val companyCode: CompanyCode
    val instant: Instant
    val price: OHLC
    val volume: Volume
}
