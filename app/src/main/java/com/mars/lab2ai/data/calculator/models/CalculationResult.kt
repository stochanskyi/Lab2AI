package com.mars.lab2ai.data.calculator.models

class CalculationResult(
    val values: FloatArray,
    val comparing: Array<FloatArray>,
    val totals: FloatArray,
    val converseTotals: FloatArray,
    val normalizedTotals: FloatArray
)