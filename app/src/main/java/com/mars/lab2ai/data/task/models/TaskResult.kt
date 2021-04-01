package com.mars.lab2ai.data.task.models

class TaskResult(
    val values: FloatArray,
    val comparing: Array<FloatArray>,
    val totals: FloatArray,
    val converseTotals: FloatArray,
    val normalizedTotals: FloatArray
)