package com.mars.lab2ai.presentation.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.mars.lab2ai.R
import com.mars.lab2ai.databinding.FragmentMainBinding
import com.mars.lab2ai.presentation.base.BaseFragment
import com.mars.lab2ai.presentation.chart.ChartActivity
import com.mars.lab2ai.presentation.main.adapter.TableAdapter
import com.mars.lab2ai.presentation.main.model.MainViewModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    override fun initViews() = with(binding) {
        super.initViews()
        listOf(
            u1EditText,
            u2EditText,
            u3EditText,
            u4EditText,
            u5EditText,
            u6EditText
        ).forEachIndexed { index, editText ->
            editText.doAfterTextChanged { viewModel.setU(index, it.toString()) }
        }

        confirmUButton.setOnClickListener { viewModel.nextAfterInputU() }

        listOf(
            mark1EditText,
            mark2EditText,
            mark3EditText,
            mark4EditText,
            mark5EditText,
        ).forEachIndexed { index, editText ->
            editText.doAfterTextChanged { viewModel.setMark(index, it.toString()) }
        }

        calculateButton.setOnClickListener { viewModel.calculate() }

        tableRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = TableAdapter()
        }

        resetButton.setOnClickListener {
            viewModel.reset()
        }
    }

    private fun initObservers() = with(binding) {
        viewModel.isUValidLiveData.observe(viewLifecycleOwner) { isConfirmEnabled ->
            confirmUButton.isEnabled = isConfirmEnabled
        }
        viewModel.isMarksValidLiveData.observe(viewLifecycleOwner) { isConfirmEnabled ->
            calculateButton.isEnabled = isConfirmEnabled
        }

        viewModel.markCharacteristicData.observe(viewLifecycleOwner) { texts ->
            marksLayout.isVisible = texts != null
            if (texts == null) return@observe
            listOf(
                markCharacteristic1,
                markCharacteristic2,
                markCharacteristic3,
                markCharacteristic4,
                markCharacteristic5
            ).forEachIndexed { index, textView -> textView.text = texts[index] }
        }

        viewModel.resultData.observe(viewLifecycleOwner) { result ->
            marksLayout.isVisible = result != null
            (binding.tableRecyclerView.adapter as? TableAdapter)?.setItems(result ?: emptyList())
        }

        viewModel.clearLiveData.observe(viewLifecycleOwner) { result ->
            if (!result) return@observe
            listOf(
                u1EditText,
                u2EditText,
                u3EditText,
                u4EditText,
                u5EditText,
                u6EditText,
                mark1EditText,
                mark2EditText,
                mark3EditText,
                mark4EditText,
                mark5EditText
            ).forEach { it.setText("") }
        }
    }

}