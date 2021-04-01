package com.mars.lab2ai.data.task

import com.mars.lab2ai.data.task.models.TaskParams
import com.mars.lab2ai.data.task.models.TaskResult
import com.mars.lab2ai.data.common.FuncReturn
import com.mars.lab2ai.data.common.successOf

class TaskResolverImpl: TaskResolver {

    override suspend fun calculate(params: TaskParams): FuncReturn<TaskResult> {
        val size = params.values.size
        val comparing = Array(size) { FloatArray(size) { 0f } }

        for (i in comparing.indices) {
            for (j in comparing[i].indices) {
                comparing[i][j] = params.weights[j] / params.weights[i]
            }
        }

        val totals = FloatArray(size) { 0f }
        val converseTotals = FloatArray(size) { 0f }
        for (i in 0 until size) {
            var sum = 0f
            for (j in 0 until size) {
                sum += comparing[i][j]
            }
            totals[i] = sum
            converseTotals[i] = 1f / sum
        }

        val maxConverseTotal = converseTotals.maxOrNull() ?: 1f

        val normalizedTotals = FloatArray(size) { 0f }
        for (i in 0 until size) {
            normalizedTotals[i] = converseTotals[i] / maxConverseTotal
        }

        return FuncReturn successOf TaskResult(
            params.values,
            comparing,
            totals,
            converseTotals,
            normalizedTotals
        )
    }
}
