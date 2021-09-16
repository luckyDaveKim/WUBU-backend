package com.wubu.api.common.web.dto.req

import org.springframework.data.domain.PageRequest

data class PagingReqDto(
    private val page: Int = 1,
    private val pageSize: Int = 10
) {

    fun getPageable(): PageRequest {
        val zeroBasePage = page - 1
        return PageRequest.of(zeroBasePage, pageSize)
    }
}
