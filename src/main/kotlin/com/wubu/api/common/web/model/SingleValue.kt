package com.wubu.api.common.web.model

import com.fasterxml.jackson.annotation.JsonValue

abstract class SingleValue<T>(
    @JsonValue
    val value: T
)
