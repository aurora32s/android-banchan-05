package com.seom.banchan.ui.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.seom.banchan.databinding.DialogDetailAddCartBinding

class DetailAddCartAlert : DialogFragment() {

    private var _binding: DialogDetailAddCartBinding? = null
    private val binding get() = _binding

    private lateinit var onClickMoveToCartBtn: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // dialog 밖을 클릭해도 종룔되지 않도록 설정
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogDetailAddCartBinding.inflate(inflater, container, false)

        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (::onClickMoveToCartBtn.isInitialized) {
            binding?.tvMoveCart?.setOnClickListener {
                onClickMoveToCartBtn()
                // backstack 으로 돌아왔을 때 dismiss 해주지 않으면 dialog 가 그대로 유지되어 있어
                // 명시적으로 dismiss 추가
                dismiss()
            }
        }
        binding?.tvCancel?.setOnClickListener { dismiss() }
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, TAG)
    }

    fun setOnClickMoveToCart(action: () -> Unit): DetailAddCartAlert {
        onClickMoveToCartBtn = action
        return this
    }

    companion object {
        const val TAG = "DetailAddAlert"

        fun build() = DetailAddCartAlert()
    }
}