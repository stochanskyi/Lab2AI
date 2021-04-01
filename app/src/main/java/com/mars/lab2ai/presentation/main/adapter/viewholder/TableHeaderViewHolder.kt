package com.mars.lab2ai.presentation.main.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mars.lab2ai.databinding.ViewHolderTableHeaderBinding

class TableHeaderViewHolder private constructor(
    private val binding: ViewHolderTableHeaderBinding
): TableViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup) = TableHeaderViewHolder(
            ViewHolderTableHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun bind(items: List<String>) {
        binding.apply {
            tableNameTextView.text = items[0]
            cell1TextView.text = items[1]
            cell2TextView.text = items[2]
            cell3TextView.text = items[3]
            cell4TextView.text = items[4]
            cell5TextView.text = items[5]
            cell6TextView.text = items[6]
        }
    }
}