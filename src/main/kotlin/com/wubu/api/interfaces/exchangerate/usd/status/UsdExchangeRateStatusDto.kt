package com.wubu.api.interfaces.exchangerate.usd.status

import com.wubu.api.common.web.model.Balance
import com.wubu.api.common.web.model.Percentage
import com.wubu.api.common.web.model.exchangerate.Rate
import kotlin.math.abs
import kotlin.math.floor

data class UsdExchangeRateStatusDto(
    val curRate: Rate,
    val beforeRate: Rate
) {
    val comparisonRate: Rate = Rate(floor(abs(curRate.value - beforeRate.value) * 100) / 100.0)
    val percentage: Percentage =
        Percentage(floor(abs(beforeRate.value - curRate.value) / beforeRate.value * 100 * 100) / 100.0)
    val balance: Balance = Balance.compare(curRate.value, beforeRate.value)

    companion object {
        fun of(beforeRate: Rate, curRate: Rate): UsdExchangeRateStatusDto {
            return UsdExchangeRateStatusDto(
                curRate = curRate,
                beforeRate = beforeRate
            )
        }
    }
}
