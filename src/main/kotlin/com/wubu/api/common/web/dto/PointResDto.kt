package com.wubu.api.common.web.dto

import com.wubu.api.common.web.model.Point

data class PointResDto(
    val data: List<PointDto>
) {
    companion object {
        fun of(points: List<Point>): PointResDto {
            val pointDtoList = points
                .map(PointDto.Companion::of)
            return PointResDto(pointDtoList)
        }
    }
}
