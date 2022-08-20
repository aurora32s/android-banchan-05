package com.seom.banchan.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.FragmentOrderListBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.ui.model.order.OrderListItemModel
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderListFragment : BaseFragment() {

    private val viewModel: OrderListViewModel by viewModels()

    private var _binding: FragmentOrderListBinding? = null
    private val binding
        get() = _binding

    private val orderListAdapter by lazy {
        ModelRecyclerAdapter<OrderListItemModel>(
            object : ModelAdapterListener {
                override fun onClick(view: View, model: Model, position: Int) {
                    if (model.type == CellType.ORDER_LIST_ITEM) {
                        // TODO 주문 상세 화면으로 이동 구현
                    }
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserver()

        viewModel.init()
    }

    /**
     * viewModel 의 flow state observing 설정
     */
    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.orderList().collect {
                Log.d(TAG, it.toString())
            }
        }
    }

    /**
     * view 의 기본 설정
     */
    private fun initViews() = binding?.let {
        it.rvOrderList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        it.rvOrderList.adapter = orderListAdapter
        orderListAdapter.submitList(mockData)
    }

    companion object {
        const val TAG = ".OrderListFragment"
        fun newInstance() = OrderListFragment()
    }
}

val mockData = (0..10).map {
    OrderListItemModel(
        orderId = it.toLong(),
        menuName = "test".repeat(it + 1),
        image = "http://public.codesquad.kr/jk/storeapp/data/main/675_ZIP_P_0057_T.jpg",
        totalPrice = it * 1000,
        menuCount = it,
        deliveryCompleted = OrderDeliveryState.DELIVERING
    )
}