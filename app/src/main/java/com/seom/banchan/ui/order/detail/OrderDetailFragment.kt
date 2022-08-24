package com.seom.banchan.ui.order.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentOrderDetailBinding
import com.seom.banchan.ui.adapter.ItemDecoration.OrderDetailItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.ext.setIconDrawable
import com.seom.banchan.util.provider.impl.ResourceProviderImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment() {

    private var _binding: FragmentOrderDetailBinding? = null
    private val binding
        get() = _binding

    private val viewModel: OrderDetailViewModel by viewModels()

    private val orderDetailAdapter by lazy {
        ModelRecyclerAdapter<Model>(
            resourceProvider = ResourceProviderImpl(requireContext())
        )
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

        val orderId = arguments?.getLong(KEY_ORDER_ID)

        if (orderId == null) {
            fragmentNavigation.popStack()
            return
        }

        initRecyclerView()
        initObserver(orderId)
    }

    private fun initRecyclerView() = binding?.let {
        it.rvOrderDetail.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        it.rvOrderDetail.addItemDecoration(
            OrderDetailItemDecoration(requireContext())
        )
        it.rvOrderDetail.adapter = orderDetailAdapter
    }

    private fun initObserver(orderId: Long) {
        lifecycleScope.launch {
            viewModel.orderDetailUiState.collect {
                when (it) {
                    OrderDetailUiState.UnInitialized -> viewModel.fetchData(orderId)
                    is OrderDetailUiState.Success -> submitOrderDetailInfo(it)
                    OrderDetailUiState.Error -> {
                        hideDetailDataAndShowWarn()
                    }
                }
            }
        }
    }

    private fun hideDetailDataAndShowWarn() = binding?.let {
        it.rvOrderDetail.isGone = true
        it.tvWarnMsg.text = getString(R.string.warn_exception_occur)
        it.ivWarnIcon.setIconDrawable(R.drawable.ic_error, true)
        it.grWarn.isVisible = true
    }

    private fun submitOrderDetailInfo(orderDetailInfo: OrderDetailUiState.Success) {
        orderDetailAdapter.submitList(
            listOf<Model>(orderDetailInfo.order) +
                    orderDetailInfo.menus +
                    listOf<Model>(orderDetailInfo.orderInfo)
        )
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