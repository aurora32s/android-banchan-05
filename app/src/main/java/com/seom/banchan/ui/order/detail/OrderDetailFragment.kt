package com.seom.banchan.ui.order.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.FragmentOrderDetailBinding
import com.seom.banchan.ui.adapter.ItemDecoration.OrderDetailItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.order.*
import com.seom.banchan.ui.order.OrderListFragment
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment() {

    private var _binding: FragmentOrderDetailBinding? = null
    private val binding
        get() = _binding

    private val orderDetailAdapter by lazy {
        ModelRecyclerAdapter<Model>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() = binding?.let {
        it.rvOrderDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        it.rvOrderDetail.addItemDecoration(
            OrderDetailItemDecoration(requireContext())
        )
        it.rvOrderDetail.adapter = orderDetailAdapter
    }

    private fun initObserver() {
        lifecycleScope.launch {
            orderDetailAdapter.submitList(mockData)
        }
    }

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

val mockData = listOf(
    OrderStateUiModel(
        orderDeliveryState = OrderDeliveryState.DELIVERING,
        createdAt = System.currentTimeMillis(),
        expectedDeliveryTime = 10 * 1000,
        menuCount = 4
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderMenuUiModel(
        menuName = "오리 주물럭_반조리",
        image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
        count = 3,
        salePrice = 6000
    ),
    OrderInfoModel(
        orderPrice = 33320
    )
)