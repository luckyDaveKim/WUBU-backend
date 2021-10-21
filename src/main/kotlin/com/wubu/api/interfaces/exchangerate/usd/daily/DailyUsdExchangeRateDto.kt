package com.wubu.api.interfaces.exchangerate.usd.daily

import com.wubu.api.common.web.dto.PointDto
import com.wubu.api.common.web.model.Point

data class DailyUsdExchangeRateRes(
    val data: List<PointDto>
) {

    companion object {
        fun of(points: List<Point>): DailyUsdExchangeRateRes {
            val pointDtoList = points
                .map(PointDto.Companion::of)
            return DailyUsdExchangeRateRes(pointDtoList)
        }
    }
}
