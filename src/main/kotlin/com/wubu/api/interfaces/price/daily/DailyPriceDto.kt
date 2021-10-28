package com.wubu.api.interfaces.price.daily

import com.wubu.api.common.web.dto.PointDto
import com.wubu.api.common.web.model.Point

data class DailyPriceRes(
    val data: List<PointDto>
) {

    companion object {
        fun of(points: List<Point>): DailyPriceRes {
            val pointDtoList = points
                .map(PointDto.Companion::of)
            return DailyPriceRes(pointDtoList)
        }
    }
}
