package com.mars.lab2ai.presentation.main.model

import androidx.lifecycle.MutableLiveData

class MainViewModelImpl : MainViewModel() {

    companion object {
        private const val DOT = '.'
    }

    override val isUValidLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    private val uArray: Array<String> = Array(6) { "" }

    override fun setU(index: Int, u: String) {
        uArray[index] = u
        validateU()
    }

    private fun validateU() {
        isUValidLiveData.value = uArray.all { it.isNotBlank() && it.isValidNumber() }
    }

    private fun CharSequence.isValidNumber(): Boolean {
        var hadComma = false
        var isCommaLast = false
        val result = all {
            when {
                it.isDigit() -> {
                    isCommaLast = false
                    true
                }
                it == DOT -> {
                    if (hadComma) false
                    else {
                        hadComma = true
                        isCommaLast = true
                        true
                    }
                }
                else -> false
            }
        }
        if (isCommaLast) return false
        return result
    }
}