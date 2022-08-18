package com.seom.banchan.ui.view.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentOrderBottomSheetBinding
import com.seom.banchan.ui.model.home.HomeMenuModel
import kotlinx.coroutines.launch

class OrderBottomSheetDialog(
    private val supportFragmentManager: FragmentManager
) : BottomSheetDialogFragment() {

    private val viewModel: OrderBottomSheetViewModel by viewModels()

    private var _binding: FragmentOrderBottomSheetBinding? = null
    private val binding get() = _binding

    private var menuModel: HomeMenuModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBottomSheetBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.menu = menuModel

        viewModel.init(menuModel)
        initBind()

        lifecycleScope.launch {
            viewModel.count.collect { binding?.count = it }
        }
        lifecycleScope.launch {
            viewModel.totalPrice.collect { binding?.totalPrice = it }
        }
    }

    private fun initBind() = binding?.let {
        it.btnCancel.setOnClickListener { dismiss() }
        it.ivDownCount.setOnClickListener {
            viewModel.decreaseCount()
        }
        it.ivUpCount.setOnClickListener {
            viewModel.increaseCount()
        }
    }

    fun setMenu(menuModel: HomeMenuModel): OrderBottomSheetDialog {
        this.menuModel = menuModel
        return this
    }

    fun show() {
        show(supportFragmentManager, TAG)
    }

    override fun getTheme(): Int {
        return R.style.RoundedBottomSheetDialog
    }

    companion object {
        const val TAG = "OrderBottomSheetDialog"
        fun build(fragmentManager: FragmentManager) =
            OrderBottomSheetDialog(fragmentManager)
    }
}