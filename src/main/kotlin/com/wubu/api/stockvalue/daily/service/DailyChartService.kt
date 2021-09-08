package com.wubu.api.stockvalue.daily.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.model.Code
import com.wubu.api.stockvalue.daily.dto.response.DailyChartsResponseDto
import com.wubu.api.stockvalue.daily.repository.DailyPriceRepository
import org.springframework.stereotype.Service

@Service
class DailyChartService(
        private val dailyPriceRepository: DailyPriceRepository
) {

    fun findDailyChart(code: Code, pagingReqDto: PagingReqDto): DailyChartsResponseDto {
        val dailyPrices = dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(code, pagingReqDto.getPageable())
        return DailyChartsResponseDto.of(dailyPrices)
    }

}