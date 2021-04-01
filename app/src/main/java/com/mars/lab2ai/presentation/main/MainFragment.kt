package com.mars.lab2ai.presentation.main

import androidx.core.widget.doAfterTextChanged
import com.mars.lab2ai.R
import com.mars.lab2ai.databinding.FragmentMainBinding
import com.mars.lab2ai.presentation.base.BaseFragment
import com.mars.lab2ai.presentation.main.model.MainViewModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun initViews() {
        super.initViews()
        binding.u1EditText.doAfterTextChanged { viewModel.setU1(it.toString()) }
        binding.u2EditText.doAfterTextChanged { viewModel.setU2(it.toString()) }
        binding.u3EditText.doAfterTextChanged { viewModel.setU3(it.toString()) }
        binding.u4EditText.doAfterTextChanged { viewModel.setU4(it.toString()) }
        binding.u5EditText.doAfterTextChanged { viewModel.setU5(it.toString()) }

        viewModel.isUValidLiveData.observe(viewLifecycleOwner) { isConfirmEnabled ->
            binding.confirmUButton.isEnabled = isConfirmEnabled
        }
    }
}