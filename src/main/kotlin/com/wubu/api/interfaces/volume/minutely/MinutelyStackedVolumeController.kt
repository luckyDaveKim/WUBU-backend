package com.wubu.api.interfaces.volume.minutely

import com.wubu.api.application.volume.minutely.MinutelyStackedVolumeFindService
import com.wubu.api.common.web.dto.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/minutely/stacked/volume")
class MinutelyStackedVolumeController(
    private val minutelyStackedVolumeFindService: MinutelyStackedVolumeFindService
) {

    @GetMapping(
        "/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findMinutelyVolumes(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate
    ): PointResDto {
        return minutelyStackedVolumeFindService.findMinutelyStackedStockValueAtDate(companyCode, date)
    }
}
