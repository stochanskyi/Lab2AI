package com.mars.lab2ai.presentation.main.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mars.lab2ai.R
import com.mars.lab2ai.app.common.launchOnComputation
import com.mars.lab2ai.data.calculator.ValueCalculator
import com.mars.lab2ai.data.calculator.models.CalculationParams
import com.mars.lab2ai.data.calculator.models.CalculationResult
import com.mars.lab2ai.data.text.TextProvider

class MainViewModelImpl(
    private val textProvider: TextProvider,
    private val calculator: ValueCalculator
) : MainViewModel() {

    companion object {
        private const val DOT = '.'
    }

    override val isUValidLiveData = MutableLiveData<Boolean>()
    override val isMarksValidLiveData = MutableLiveData<Boolean>()

    override val markCharacteristicData = MutableLiveData<List<String>?>(null)

    private val uArray: Array<String> = Array(6) { "" }

    private val markArray: Array<String> = Array(5) { "" }

    override fun setU(index: Int, u: String) {
        uArray[index] = u
        validateU()
    }

    override fun nextAfterInputU() {
        validateU()
        if (isUValidLiveData.value != true) return
        markCharacteristicData.value = generateCharacteristicData()
    }

    override fun setMark(index: Int, mark: String) {
        markArray[index] = mark
        validateMarks()
    }

    override fun calculate() {
        validateMarks()
        if (isMarksValidLiveData.value != true) return
        viewModelScope.launchOnComputation {
            val result = safeCall {
                calculator.calculate(
                    CalculationParams(
                        uArray.map { it.toFloat() }.toFloatArray(),
                        markArray.map { it.toFloat() }.toFloatArray()
                    )
                )
            } ?: return@launchOnComputation


        }
    }

    private fun validateU() {
        isUValidLiveData.value = uArray.all { it.isValidNumber() }
    }

    private fun validateMarks() {
        isMarksValidLiveData.value = markArray.all { it.isValidNumber() }
    }

    private fun generateCharacteristicData(): List<String> {
        val main = uArray.last()
        return List(uArray.size - 1) {
            textProvider.getText(R.string.advantage, main, uArray[it])
        }
    }

    private fun generateViewData(result: CalculationResult): List<List<String>> {
        return emptyList()
    }

    private fun CharSequence.isValidNumber(): Boolean {
        if (isEmpty()) return false

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