package com.wubu.api.interfaces.volume.daily

import com.wubu.api.common.web.dto.PointDto
import com.wubu.api.common.web.model.Point

data class DailyVolumeRes(
    val data: List<PointDto>
) {

    companion object {
        fun of(points: List<Point>): DailyVolumeRes {
            val pointDtoList = points
                .map(PointDto.Companion::of)
            return DailyVolumeRes(pointDtoList)
        }
    }
}
