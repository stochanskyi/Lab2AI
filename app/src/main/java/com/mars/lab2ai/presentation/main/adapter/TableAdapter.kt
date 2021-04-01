package com.mars.lab2ai.presentation.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.lab2ai.presentation.main.adapter.viewholder.TableHeaderViewHolder
import com.mars.lab2ai.presentation.main.adapter.viewholder.TableRawViewHolder
import com.mars.lab2ai.presentation.main.adapter.viewholder.TableViewHolder
import java.lang.IllegalStateException

private const val HEADER_VIE_TYPE = 1
private const val RAW_VIE_TYPE = 2

class TableAdapter : RecyclerView.Adapter<TableViewHolder>() {

    private val items: MutableList<List<String>> = mutableListOf()

    fun setItems(items: List<List<String>>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = if (position == 0) HEADER_VIE_TYPE else RAW_VIE_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        return when(viewType) {
            HEADER_VIE_TYPE -> TableHeaderViewHolder.create(parent)
            RAW_VIE_TYPE -> TableRawViewHolder.create(parent)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}