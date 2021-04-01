package com.mars.lab2ai.presentation.main.model

import androidx.lifecycle.MutableLiveData

class MainViewModelImpl : MainViewModel() {

    companion object {
        private const val DOT = '.'
    }

    override val isUValidLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    private val u: Array<String> = Array(6) { "" }

    override fun setU1(u1: String) {
        u[0] = u1
        validateU()
    }

    override fun setU2(u1: String) {
        u[1] = u1
        validateU()
    }

    override fun setU3(u1: String) {
        u[2] = u1
        validateU()
    }

    override fun setU4(u1: String) {
        u[3] = u1
        validateU()
    }

    override fun setU5(u1: String) {
        u[4] = u1
        validateU()
    }

    override fun setU6(u1: String) {
        u[5] = u1
        validateU()
    }

    private fun validateU() {
        isUValidLiveData.value = u.all { it.isNotBlank() && it.isValidNumber() }
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