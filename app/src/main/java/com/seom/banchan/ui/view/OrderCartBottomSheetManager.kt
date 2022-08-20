package com.seom.banchan.ui.view

import androidx.fragment.app.FragmentManager
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.view.bottomsheet.OrderBottomSheetDialog
import com.seom.banchan.ui.view.dialog.DetailAddCartAlert

/**
 * 장바구니 추가 Bottom Sheet 와 장바구니 추가 성공 시 보여주는
 * Dialog 를 한번에 관리하는 class
 */
class OrderCartBottomSheetManager(
    private val fragmentManager: FragmentManager
) {
    private lateinit var onClickMoveToCartListener: () -> Unit

    private fun handleAfterAddToCart() {
        DetailAddCartAlert.build()
            .setOnClickMoveToCart {
                if (::onClickMoveToCartListener.isInitialized) {
                    onClickMoveToCartListener()
                }
            }
            .show(fragmentManager)
    }

    fun setOnClickMoveToCartListener(onClickMoveToCartListener: () -> Unit): OrderCartBottomSheetManager {
        this.onClickMoveToCartListener = onClickMoveToCartListener
        return this
    }

    fun show(currentMenuModel: HomeMenuModel) {
        OrderBottomSheetDialog.build(fragmentManager)
            .setMenu(currentMenuModel)
            .setOnSuccessAddToCart(::handleAfterAddToCart)
            .show()
    }

    companion object {
        fun build(fragmentManager: FragmentManager) = OrderCartBottomSheetManager(fragmentManager)
    }
}