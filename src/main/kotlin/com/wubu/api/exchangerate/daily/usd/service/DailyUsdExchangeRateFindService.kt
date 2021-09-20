package com.wubu.api.exchangerate.daily.usd.service

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.exchangerate.daily.usd.binding.DailyUsdExchangeRateConverter.DailyUsdExchangeRateToPointConverter
import com.wubu.api.exchangerate.daily.usd.repository.DailyUsdExchangeRateRepository
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
