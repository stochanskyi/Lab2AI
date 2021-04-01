package com.mars.lab2ai.data.common

typealias CallResultList<T> = FuncReturn<List<T>>
typealias CallResultNothing = FuncReturn<Unit>

private typealias Transformer<T, U> = T.() -> U
private typealias InlineTransformer<T, U> = T.() -> FuncReturn<U>

sealed class FuncReturn<T> {

    class Success<T>(val data: T) : FuncReturn<T>()

    open class Error<T>(
        val errorType: String? = null,
        val message: String
    ) : FuncReturn<T>() {

        constructor(message: String) : this(null, message)
    }

    class UnknownError<T> : Error<T>("")

    fun <U> transform(transformer: Transformer<T, U>): FuncReturn<U> {
        return when (this) {
            is Success -> Success(transformer(data))
            is Error -> Error(errorType, message)
        }
    }

    fun <U> transformInline(transformer: InlineTransformer<T, U>): FuncReturn<U> {
        return when (this) {
            is Success -> transformer(data)
            is Error -> Error(errorType, message)
        }
    }

    fun doOnSuccess(action: (T) -> Unit) = apply {
        if (this is Success) {
            action(data)
        }
    }

    fun doOnError(action: () -> Unit) = apply {
        if (this is Error) {
            action()
        }
    }

    fun ignoreData(): CallResultNothing {
        return when (this) {
            is Success -> Success(Unit)
            is Error -> Error(errorType, message)
        }
    }

    companion object {
        suspend fun <T> anySuccess(
            specificError: Error<T>.() -> Boolean,
            noSuccess: () -> FuncReturn<T>,
            vararg sources: suspend () -> FuncReturn<T>,
        ): FuncReturn<T> {
            sources.forEach {
                when (val callResult = it()) {
                    is Success<*> -> return callResult
                    is Error -> {
                        if (!specificError(callResult)) return callResult
                    }
                }
            }
            return noSuccess()
        }
    }
}

infix fun <T> FuncReturn.Companion.successOf(value: T) = FuncReturn.Success(value)

fun <T, U> FuncReturn<List<T>>.transformList(transformer: Transformer<T, U>) = transform {
    map { it.transformer() }
}