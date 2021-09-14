package com.wubu.api.stockvalue.daily.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.service.StockValueFindService
import java.time.LocalDate

interface DailyStockValueFindService : StockValueFindService {
    fun findDailyStockValue(code: Code, pagingReqDto: PagingReqDto): PointResDto
    fun findThisWeekStockValue(code: Code, date: LocalDate = LocalDate.now()): PointResDto
}