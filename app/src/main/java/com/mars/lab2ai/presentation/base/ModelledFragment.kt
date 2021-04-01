package com.mars.lab2ai.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes

abstract class ModelledFragment : BaseFragment {

    constructor() : super()

    @ContentView
    constructor (@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected abstract val model: BaseViewModelContract

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.observeErrors()
    }


}