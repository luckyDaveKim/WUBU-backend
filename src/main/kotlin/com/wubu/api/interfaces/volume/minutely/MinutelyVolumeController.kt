package com.wubu.api.interfaces.volume.minutely

import com.wubu.api.application.volume.minutely.MinutelyVolumeFacade
import com.wubu.api.common.web.dto.PagingReqDto
import com.wubu.api.common.web.model.CompanyCode
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/minutely/volume")
class MinutelyVolumeController(
    private val minutelyVolumeFacade: MinutelyVolumeFacade
) {

    @GetMapping(
        "/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findMinutelyVolumes(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @ModelAttribute pagingReqDto: PagingReqDto
    ): MinutelyVolumeRes {
        return minutelyVolumeFacade.retrieveMinutelyVolumes(companyCode, pagingReqDto)
    }

    @GetMapping(
        "/{date}/companies/{companyCode}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findMinutelyVolumesAtDate(
        @PathVariable(value = "companyCode") companyCode: CompanyCode,
        @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
    ): MinutelyVolumeRes {
        return minutelyVolumeFacade.retrieveMinutelyVolumesAtDate(companyCode, date)
    }
}
