package com.mars.lab2ai.data.text

import androidx.annotation.StringRes

interface TextProvider {

    fun getText(@StringRes textId: Int, vararg params: Any): String

}