package com.seom.banchan.ui.view.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentOrderBottomSheetBinding
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.home.HomeMenuModel

class OrderBottomSheetDialog(
    private val supportFragmentManager: FragmentManager
) : BottomSheetDialogFragment() {

    private var _binding: FragmentOrderBottomSheetBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun initBind(menuModel: HomeMenuModel) = binding?.let {
        it.menu = menuModel
    }

    fun show(menuModel: HomeMenuModel) {
        initBind(menuModel)
        show(supportFragmentManager, TAG)
    }

    override fun getTheme(): Int {
        return R.style.RoundedBottomSheetDialog
    }

    companion object {
        const val TAG = "OrderBottomSheetDialog"
        fun getInstance(fragmentManager: FragmentManager) =
            OrderBottomSheetDialog(fragmentManager)
    }
}