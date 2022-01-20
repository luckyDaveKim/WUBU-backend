package com.wubu.api.domain.stock.daily

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class DailyStockPieceServiceImplTest {

    @Mock
    private lateinit var dailyStockPieceReader: DailyStockPieceReader

    @InjectMocks
    private lateinit var dailyStockServiceImpl: DailyStockService

}
