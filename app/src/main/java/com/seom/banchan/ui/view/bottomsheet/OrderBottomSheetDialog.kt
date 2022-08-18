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

    private var menuModel: HomeMenuModel? = null

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
        it.menu = menuModel
        it.btnCancel.setOnClickListener { dismiss() }
    }

    fun show(menuModel: HomeMenuModel) {
        this.menuModel = menuModel
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