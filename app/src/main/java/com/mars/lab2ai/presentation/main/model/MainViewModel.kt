package com.mars.lab2ai.presentation.main.model

import androidx.lifecycle.LiveData
import com.mars.lab2ai.presentation.base.BaseViewModel

abstract class MainViewModel : BaseViewModel() {

    abstract val isUValidLiveData: LiveData<Boolean>

    abstract val markCharacteristicData: LiveData<List<String>>

    abstract fun setU(index: Int, u: String)

    abstract fun nextAfterInputU()

}