package com.seom.banchan.ui.order.detail

import android.os.Bundle
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.order.OrderListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment() {


    companion object {
        const val TAG = ".OrderDetailFragment"
        const val KEY_ORDER_ID = "SelectedOrderId"

        fun newInstance(orderId: Long): OrderDetailFragment {
            val instance = OrderDetailFragment()

            val bundle = Bundle()
            bundle.putLong(KEY_ORDER_ID, orderId)
            instance.arguments = bundle

            return instance
        }
    }
}