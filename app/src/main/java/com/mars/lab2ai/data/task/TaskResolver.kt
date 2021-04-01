package com.mars.lab2ai.data.task

import com.mars.lab2ai.data.task.models.TaskParams
import com.mars.lab2ai.data.task.models.TaskResult
import com.mars.lab2ai.data.common.FuncReturn

interface TaskResolver {

    suspend fun calculate(params: TaskParams): FuncReturn<TaskResult>

}