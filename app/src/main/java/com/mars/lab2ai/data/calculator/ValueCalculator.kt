package com.mars.lab2ai.data.calculator

import com.mars.lab2ai.data.calculator.models.CalculationParams
import com.mars.lab2ai.data.calculator.models.CalculationResult
import com.mars.lab2ai.data.common.CallResult

interface ValueCalculator {

    suspend fun calculate(params: CalculationParams): CallResult<CalculationResult>

}