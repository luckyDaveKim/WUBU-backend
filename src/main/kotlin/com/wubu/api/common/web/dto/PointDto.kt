package com.wubu.api.common.web.dto

import com.wubu.api.common.web.model.Point

data class PointDto(
    val x: Number,
    val y: Number,
    val z: Number,
    val open: Number,
    val high: Number,
    val low: Number,
    val close: Number
) {
    companion object {
        fun of(point: Point): PointDto {
            return PointDto(
                x = point.x,
                y = point.y,
                z = point.z,
                open = point.open,
                high = point.high,
                low = point.low,
                close = point.close
            )
        }
    }
}
