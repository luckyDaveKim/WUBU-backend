package com.wubu.api.interfaces.exchangerate.usd.minutely

import com.wubu.api.common.web.dto.PointDto
import com.wubu.api.common.web.model.Point

data class MinutelyUsdExchangeRateRes(
    val data: List<PointDto>
) {

    companion object {
        fun of(points: List<Point>): MinutelyUsdExchangeRateRes {
            val pointDtoList = points
                .map(PointDto.Companion::of)
            return MinutelyUsdExchangeRateRes(pointDtoList)
        }
    }
}
