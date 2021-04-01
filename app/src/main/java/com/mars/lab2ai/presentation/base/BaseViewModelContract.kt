package com.mars.lab2ai.presentation.base

import androidx.lifecycle.LiveData

interface BaseViewModelContract {

    val errorData: LiveData<String>

}