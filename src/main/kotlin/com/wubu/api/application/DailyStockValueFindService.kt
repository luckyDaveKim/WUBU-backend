package com.wubu.api.application

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import java.time.LocalDate

interface DailyStockValueFindService : StockValueFindService {
    fun findDailyStockValue(companyCode: CompanyCode, pagingReqDto: PagingReqDto): PointResDto
    fun findThisWeekStockValue(companyCode: CompanyCode, date: LocalDate = LocalDate.now()): PointResDto
}
