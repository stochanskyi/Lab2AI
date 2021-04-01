package com.mars.lab2ai.app.di

import com.mars.lab2ai.data.calculator.ValueCalculator
import com.mars.lab2ai.data.calculator.ValueCalculatorImpl
import org.koin.dsl.module

val DataModule = module {

    single<ValueCalculator> { ValueCalculatorImpl() }

}