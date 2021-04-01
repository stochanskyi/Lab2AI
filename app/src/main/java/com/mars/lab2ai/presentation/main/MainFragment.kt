package com.mars.lab2ai.presentation.main

import com.mars.lab2ai.R
import com.mars.lab2ai.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = MainFragment()
    }
}