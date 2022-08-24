package com.seom.banchan.ui.view.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentOrderBottomSheetBinding
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.ext.repeatLaunch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderBottomSheetDialog(
    private val supportFragmentManager: FragmentManager
) : BottomSheetDialogFragment() {

    private val viewModel: OrderBottomSheetViewModel by viewModels()

    private var _binding: FragmentOrderBottomSheetBinding? = null
    private val binding get() = _binding

    private var menuModel: HomeMenuModel? = null
    private lateinit var onSuccessAddToCart: () -> Unit

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

        initBind()
        initObserver()
    }

    private fun initBind() = binding?.let {
        it.btnCancel.setOnClickListener { dismiss() }
        it.btnOrder.setOnClickListener { viewModel.addMenuToCart() }
        it.ivDownCount.setOnClickListener {
            viewModel.decreaseCount()
        }
        it.ivUpCount.setOnClickListener {
            viewModel.increaseCount()
        }
    }

    private fun initObserver() {
        repeatLaunch {
            // 현재 선택된 개수
            launch { viewModel.count.collect { binding?.count = it } }
            // 총 금액
            launch { viewModel.totalPrice.collect { binding?.totalPrice = it } }
            // uiState
            launch { observeUiState() }
        }
    }

    private suspend fun observeUiState() {
        viewModel.orderBottomSheetUiState.collect {
            when (it) {
                OrderBottomSheetUiState.UnInitialized -> viewModel.init(menuModel)
                OrderBottomSheetUiState.SuccessAddToCart -> {
                    if (::onSuccessAddToCart.isInitialized) {
                        onSuccessAddToCart()
                        dismiss()
                    }
                }
                OrderBottomSheetUiState.FailAddToCart -> {

                }
            }
        }
    }

    /**
     * bottom sheet 에 보여줄 메뉴 setting
     */
    fun setMenu(menuModel: HomeMenuModel): OrderBottomSheetDialog {
        this.menuModel = menuModel
        return this
    }

    /**
     * 장바구니 추가 성공 시 호출할 event setting
     */
    fun setOnSuccessAddToCart(onSuccessAddToCart: () -> Unit): OrderBottomSheetDialog {
        this.onSuccessAddToCart = onSuccessAddToCart
        return this
    }

    fun show() {
        show(supportFragmentManager, TAG)
    }

    /**
     * bottom sheet style 지정
     */
    override fun getTheme(): Int {
        return R.style.RoundedBottomSheetDialog
    }

    companion object {
        const val TAG = "OrderBottomSheetDialog"
        fun build(fragmentManager: FragmentManager) =
            OrderBottomSheetDialog(fragmentManager)
    }
}