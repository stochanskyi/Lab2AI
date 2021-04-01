package com.mars.lab2ai.presentation.main.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TableViewHolder protected constructor(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(items: List<String>)
}