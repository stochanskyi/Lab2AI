package com.mars.lab2ai.data.text

import android.content.Context

class TextProviderImpl(
    private val context: Context
) : TextProvider {

    override fun getText(textId: Int, vararg params: Any): String {
        return context.getString(textId, *params)
    }
}