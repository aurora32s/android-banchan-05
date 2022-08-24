package com.seom.banchan.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentOrderListBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.order.OrderListItemUiModel
import com.seom.banchan.ui.order.detail.OrderDetailFragment
import com.seom.banchan.util.exception.DatabaseFlowException
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.ext.setIconDrawable
import com.seom.banchan.util.ext.snackBarForReRequest
import com.seom.banchan.util.listener.ModelAdapterListener
import com.seom.banchan.util.provider.impl.ResourceProviderImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListFragment : BaseFragment() {

    private val viewModel: OrderListViewModel by viewModels()

    private var _binding: FragmentOrderListBinding? = null
    private val binding
        get() = _binding

    private val orderListAdapter by lazy {
        ModelRecyclerAdapter<OrderListItemUiModel>(
            resourceProvider = ResourceProviderImpl(requireContext()),
            modelAdapterListener = object : ModelAdapterListener {
                override fun onClick(view: View, model: Model, position: Int) {
                    if (model.type == CellType.ORDER_LIST_ITEM) {
                        val orderId = (model as OrderListItemUiModel).orderId
                        fragmentNavigation.replaceFragment(
                            OrderDetailFragment.newInstance(orderId),
                            OrderDetailFragment.TAG
                        )
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
    }

    /**
     * viewModel 의 flow state observing 설정
     */
    private fun initObserver() {
        repeatLaunch {
            try {
                viewModel.orderList().collect {
                    if (it.isEmpty()) {
                        hideList()
                    } else {
                        showList()
                        orderListAdapter.submitList(it)
                    }
                }
            } catch (exception: DatabaseFlowException) {
                showReRequestSnackBar()
            }
        }
    }

    private fun showList() = binding?.let {
        it.grWarn.isGone = true
        it.rvOrderList.isVisible = true
    }

    private fun hideList() = binding?.let {
        it.rvOrderList.isGone = true
        it.tvWarnMsg.text = getString(R.string.warn_none_data)
        it.ivWarnIcon.setIconDrawable(R.drawable.ic_common_icon)
        it.grWarn.isVisible = true
    }

    private fun showReRequestSnackBar() = binding?.let {
        it.rvOrderList.isGone = true
        it.tvWarnMsg.text = getString(R.string.warn_exception_occur)
        it.ivWarnIcon.setIconDrawable(R.drawable.ic_error, true)
        it.grWarn.isVisible = true
        requireContext().snackBarForReRequest(it.root) {
            initObserver()
        }
    }

    /**
     * view 의 기본 설정
     */
    private fun initViews() = binding?.let {
        it.rvOrderList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        it.rvOrderList.adapter = orderListAdapter
    }

    companion object {
        const val TAG = ".OrderListFragment"
        fun newInstance() = OrderListFragment()
    }
}