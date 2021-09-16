package com.wubu.api.stockvalue.daily.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.stockvalue.service.StockValueFindService
import java.time.LocalDate

interface DailyStockValueFindService : StockValueFindService {
    fun findDailyStockValue(companyCode: CompanyCode, pagingReqDto: PagingReqDto): PointResDto
    fun findThisWeekStockValue(companyCode: CompanyCode, date: LocalDate = LocalDate.now()): PointResDto
}