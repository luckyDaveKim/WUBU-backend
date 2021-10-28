package com.wubu.api.interfaces.price.minutely

import com.wubu.api.common.web.dto.PointDto
import com.wubu.api.common.web.model.Point

data class MinutelyPriceRes(
    val data: List<PointDto>
) {

    companion object {
        fun of(points: List<Point>): MinutelyPriceRes {
            val pointDtoList = points
                .map(PointDto.Companion::of)
            return MinutelyPriceRes(pointDtoList)
        }
    }
}
