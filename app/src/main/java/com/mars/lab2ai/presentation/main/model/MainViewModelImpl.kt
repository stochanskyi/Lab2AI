package com.mars.lab2ai.presentation.main.model

import androidx.lifecycle.MutableLiveData
import com.mars.lab2ai.R
import com.mars.lab2ai.data.calculator.ValueCalculator
import com.mars.lab2ai.data.text.TextProvider

class MainViewModelImpl(
    private val textProvider: TextProvider,
    private val calculator: ValueCalculator
) : MainViewModel() {

    companion object {
        private const val DOT = '.'
    }

    override val isUValidLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    override val markCharacteristicData = MutableLiveData<List<String>>()

    private val uArray: Array<String> = Array(6) { "" }

    override fun setU(index: Int, u: String) {
        uArray[index] = u
        validateU()
    }

    override fun nextAfterInputU() {
        validateU()
        if (isUValidLiveData.value != true) return
        markCharacteristicData.value = generateCharacteristicData()
    }

    private fun validateU() {
        isUValidLiveData.value = uArray.all { it.isNotBlank() && it.isValidNumber() }
    }

    private fun generateCharacteristicData(): List<String> {
        val main = uArray.last()
        return List(uArray.size - 1) {
            textProvider.getText(R.string.advantage, main, uArray[it])
        }
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