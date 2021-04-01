package com.mars.lab2ai.app.common

import kotlinx.coroutines.*

suspend fun <T> withIComputationContext(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.Default, block)
}

suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.IO, block)
}

suspend fun <T> withMainContext(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.Main, block)
}

suspend fun <T> withUnconfinedContext(block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.Unconfined, block)
}

fun CoroutineScope.launchOnComputation(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.Default, start, block)

fun CoroutineScope.launchOnIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.IO, start, block)

fun CoroutineScope.launchOnMain(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.Main, start, block)

fun CoroutineScope.launchOnUnconfined(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(Dispatchers.Unconfined, start, block)