package com.mars.lab2ai.app.common

typealias Action = () -> Unit

fun <T> MutableCollection<T>.clearAndAdd(other: Collection<T>) {
    clear()
    addAll(other)
}


fun String.takeIfNotEmpty() = takeIf { it.isNotEmpty() }