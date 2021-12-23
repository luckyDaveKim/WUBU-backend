package com.wubu.api.domain.stock.daily

import com.wubu.api.common.web.model.CompanyCode
import java.time.LocalDate

data class DailyStockPieceId(
    var companyCode: CompanyCode,
    var date: LocalDate
)