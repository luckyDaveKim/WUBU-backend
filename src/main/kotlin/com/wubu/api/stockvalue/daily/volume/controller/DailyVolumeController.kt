package com.wubu.api.stockvalue.daily.volume.controller

import com.wubu.api.common.web.dto.req.PagingReqDto
import com.wubu.api.common.web.dto.res.PointResDto
import com.wubu.api.common.web.model.CompanyCode
import com.wubu.api.stockvalue.daily.volume.service.DailyVolumeFindService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/daily/volume")
class DailyVolumeController(
        private val dailyVolumeFindService: DailyVolumeFindService
) {

    @GetMapping(
            "/companies/{companyCode}",
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findDailyVolumes(
            @PathVariable(value = "companyCode") companyCode: CompanyCode,
            @ModelAttribute pagingReqDto: PagingReqDto): PointResDto {
        return dailyVolumeFindService.findDailyStockValue(companyCode, pagingReqDto)
    }

}