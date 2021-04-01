package com.mars.lab2ai.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mars.lab2ai.app.common.takeIfNotEmpty
import com.mars.lab2ai.app.common.withMainContext
import com.mars.lab2ai.data.common.CallResult

abstract class BaseViewModel : ViewModel(), BaseViewModelContract {

    override val errorData = MutableLiveData<String>()

    protected suspend fun <T> safeCall(action: suspend () -> CallResult<T>): T? {
        return try {
            val result = action()
            processRequestResult(result)
        } catch (e: Exception) {
            val isErrorHandled = withMainContext {
                handleSafeCallError()
            }
            if (isErrorHandled) return null
            e.printStackTrace()
            showError(e.message)
            null
        }
    }

    protected open suspend fun <T> processRequestResult(result: CallResult<T>): T? {
        if (result is CallResult.Error) {
            val isErrorHandled = withMainContext {
                handleCallResultError(result)
            }
            if (isErrorHandled) return null
            showError(result.message)
            return null
        }

        return (result as CallResult.Success<T>).data
    }

    protected fun handleSafeCallError(): Boolean {
        return false
    }

    protected fun <T> handleCallResultError(error: CallResult.Error<T>): Boolean {
        when (error.errorType) {
            else -> return false
        }
        return true
    }

    protected suspend fun showError(message: String?) {
        if (message?.takeIfNotEmpty() == null) return
        withMainContext {
            errorData.value = message
        }
    }

}