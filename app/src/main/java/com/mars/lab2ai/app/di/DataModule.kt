package com.mars.lab2ai.app.di

import com.mars.lab2ai.data.task.TaskResolver
import com.mars.lab2ai.data.task.TaskResolverImpl
import com.mars.lab2ai.data.text.TextProvider
import com.mars.lab2ai.data.text.TextProviderImpl
import org.koin.dsl.module

val DataModule = module {

    single<TextProvider> { TextProviderImpl(get()) }

    single<TaskResolver> { TaskResolverImpl() }

}