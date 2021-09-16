package com.wubu.api.common.web.dto

import com.wubu.api.common.web.model.Point

data class PointDto(
    val x: Long,
    val y: Long,
    val z: Long,
    val open: Long,
    val high: Long,
    val low: Long,
    val close: Long
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
