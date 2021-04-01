package com.mars.lab2ai.presentation.main.model

import androidx.lifecycle.LiveData
import com.mars.lab2ai.presentation.base.BaseViewModel

abstract class MainViewModel : BaseViewModel() {

    abstract val isUValidLiveData: LiveData<Boolean>
    abstract val isMarksValidLiveData: LiveData<Boolean>

    abstract val clearLiveData: LiveData<Boolean>

    abstract val markCharacteristicData: LiveData<List<String>?>

    abstract val resultData: LiveData<List<List<String>>?>

    abstract fun setU(index: Int, u: String)

    abstract fun nextAfterInputU()

    abstract fun setMark(index: Int, mark: String)

    abstract fun calculate()

    abstract fun reset()

}