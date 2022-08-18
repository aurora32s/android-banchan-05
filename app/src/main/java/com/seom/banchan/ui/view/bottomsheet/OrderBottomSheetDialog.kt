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

class OrderBottomSheetDialog(
    private val supportFragmentManager: FragmentManager,
    private val detailMenuModel: DetailMenuUiModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBind()
    }

    private fun initBind() = binding?.let {
    }

    fun show() {
        show(supportFragmentManager, TAG)
    }

    override fun getTheme(): Int {
        return R.style.RoundedBottomSheetDialog
    }

    companion object {
        const val TAG = "OrderBottomSheetDialog"
        fun getInstance(fragmentManager: FragmentManager, detailMenuModel: DetailMenuUiModel) =
            OrderBottomSheetDialog(fragmentManager, detailMenuModel)
    }
}