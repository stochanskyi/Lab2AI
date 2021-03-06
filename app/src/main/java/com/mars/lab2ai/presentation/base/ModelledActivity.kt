package com.mars.lab2ai.presentation.base

import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes

abstract class ModelledActivity : BaseActivity {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected abstract val model: BaseViewModelContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.observeErrors()
    }

}