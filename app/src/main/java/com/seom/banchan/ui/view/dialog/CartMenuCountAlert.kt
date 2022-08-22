package com.seom.banchan.ui.view.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.seom.banchan.databinding.DialogCartMenuCountBinding
import java.util.regex.Pattern

class CartMenuCountAlert(
    private val currentCount : Int
) : DialogFragment() {

    private var _binding: DialogCartMenuCountBinding? = null
    private val binding get() = _binding

    private lateinit var onClickOkBtn: (Int) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 다이얼로그에 바깥을 눌러도 제거되도록 수정
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCartMenuCountBinding.inflate(inflater, container, false)
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.etCount?.setText(currentCount.toString())
        binding?.etCount?.requestFocus()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        if (::onClickOkBtn.isInitialized) {
            binding?.tvOk?.setOnClickListener {
                val count = (binding?.etCount?.editableText.toString())
                if(Pattern.matches("^[0-9]*\$",count) && count.isNotBlank() && count.toInt() > 0) {
                    onClickOkBtn(count.toInt())
                    // backstack 으로 돌아왔을 때 dismiss 해주지 않으면 dialog 가 그대로 유지되어 있어
                    // 명시적으로 dismiss 추가
                    dismiss()
                }
            }
        }
        binding?.tvCancel?.setOnClickListener { dismiss() }
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, TAG)
    }

    fun setOnClickMoveToCart(action: (Int) -> Unit): CartMenuCountAlert {
        onClickOkBtn = action
        return this
    }

    companion object {
        const val TAG = "CartMenuCountAlert"

        fun build(currentCount: Int) = CartMenuCountAlert(currentCount)
    }
}