package com.mars.lab2ai.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class MainViewModelImpl : MainViewModel() {

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
        isUValidLiveData.value = u.all { it.isNotBlank() }
    }
}