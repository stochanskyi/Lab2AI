package com.mars.lab2ai.presentation.main.model

import androidx.lifecycle.LiveData
import com.mars.lab2ai.presentation.base.BaseViewModel

abstract class MainViewModel : BaseViewModel() {

    abstract val isUValidLiveData: LiveData<Boolean>

    abstract fun setU1(u1: String)
    abstract fun setU2(u1: String)
    abstract fun setU3(u1: String)
    abstract fun setU4(u1: String)
    abstract fun setU5(u1: String)
    abstract fun setU6(u1: String)

}