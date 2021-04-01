package com.mars.lab2ai.app.di

import com.mars.lab2ai.presentation.main.model.MainViewModel
import com.mars.lab2ai.presentation.main.model.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel<MainViewModel> { MainViewModelImpl() }

}