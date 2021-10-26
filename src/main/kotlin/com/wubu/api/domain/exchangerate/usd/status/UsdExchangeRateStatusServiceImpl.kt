package com.wubu.api.domain.exchangerate.usd.status

import com.wubu.api.application.exchangerate.usd.status.UsdExchangeRateStatusNotFoundException
import com.wubu.api.interfaces.exchangerate.usd.status.UsdExchangeRateStatusRes
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UsdExchangeRateStatusServiceImpl(
    private val usdExchangeRateStatusReader: UsdExchangeRateStatusReader
) : UsdExchangeRateStatusService {

    override fun retrieveExchangeRateStatusAtDate(date: LocalDate): UsdExchangeRateStatusRes {
        val curDate = date
        val curRate = usdExchangeRateStatusReader.getLastExchangeRateAtDate(curDate)?.id?.rate
            ?: throw UsdExchangeRateStatusNotFoundException(curDate)

        val beforeDate = date.minusDays(1)
        val beforeRate = usdExchangeRateStatusReader.getLastExchangeRateAtDate(beforeDate)?.id?.rate
            ?: throw UsdExchangeRateStatusNotFoundException(beforeDate)

        return UsdExchangeRateStatusRes(
            curRate = curRate,
            beforeRate = beforeRate
        )
    }
}
