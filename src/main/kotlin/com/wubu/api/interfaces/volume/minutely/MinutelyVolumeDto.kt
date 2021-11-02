package com.wubu.api.interfaces.volume.minutely

import com.wubu.api.common.web.dto.PointDto
import com.wubu.api.common.web.model.Point

data class MinutelyVolumeRes(
    val data: List<PointDto>
) {

    companion object {
        fun of(points: List<Point>): MinutelyVolumeRes {
            val pointDtoList = points
                .map(PointDto.Companion::of)
            return MinutelyVolumeRes(pointDtoList)
        }
    }
}
