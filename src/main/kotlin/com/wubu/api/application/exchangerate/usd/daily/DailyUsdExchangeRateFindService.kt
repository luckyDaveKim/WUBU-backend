package com.wubu.api.application.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.infra.exchangerate.usd.daily.DailyUsdExchangeRateRepository
import com.wubu.api.interfaces.exchangerate.usd.daily.DailyUsdExchangeRateConverter.DailyUsdExchangeRateToPointConverter
import org.springframework.stereotype.Service

@Service
class DailyUsdExchangeRateFindService(
    private val dailyUsdExchangeRateRepository: DailyUsdExchangeRateRepository,
    private val dailyUsdExchangeRateToPointConverter: DailyUsdExchangeRateToPointConverter
) {
    fun findDailyExchangeRate(pagingReqDto: PagingReqDto): PointResDto {
        val points =
            dailyUsdExchangeRateRepository.findAllByOrderById_DateDesc(
                pagingReqDto.getPageable()
            )
                .reversed()
                .map(dailyUsdExchangeRateToPointConverter::convert)
                .toList()

        return PointResDto.of(points)
    }
}
