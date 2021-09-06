package com.wubu.api.chart.daily.service

import com.wubu.api.chart.daily.dto.response.DailyChartsResponseDto
import com.wubu.api.price.daily.model.Code
import com.wubu.api.price.daily.repository.DailyPriceRepository
import org.springframework.stereotype.Service

@Service
class DailyChartService(
        private val dailyPriceRepository: DailyPriceRepository
) {

    fun findDailyChart(code: Code): DailyChartsResponseDto {
        val dailyPrices = dailyPriceRepository.findAllByIdCodeOrderByIdDateAsc(code)
        return DailyChartsResponseDto.of(dailyPrices)
    }

}