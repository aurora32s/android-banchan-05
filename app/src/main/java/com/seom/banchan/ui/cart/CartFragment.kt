package com.seom.banchan.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.FragmentCartBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.ui.model.cart.CartMenuModel
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class CartFragment : BaseFragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding

    private val cartAdapter: ModelRecyclerAdapter<Model> by lazy { ModelRecyclerAdapter() }

    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() = binding?.let {
        it.rvCart.adapter = cartAdapter
        it.rvCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        cartAdapter.submitList(
            //임시 데이터
            listOf(
                CartCheckModel(
                    id = "cart_check"
                ),
                OrderInfoModel(
                    id = "order_info",
                    orderPrice = 0
                ),
                CartOrderModel(
                    id = "cart_order",
                    totalPrice = 0,
                ),
                CartRecentModel(
                    id = "cart_recent"
                )
            )
        )
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.cartMenus.collectLatest {
                cartAdapter.updateModelsAtPosition(it,1,it.size)
            }
        }
        lifecycleScope.launch{
            viewModel.orderInfo.collectLatest {
                cartAdapter.updateModelAtPosition(it,viewModel.cartMenus.value.size + 1)
            }
        }
        lifecycleScope.launch{
            viewModel.cartOrder.collectLatest {
                cartAdapter.updateModelAtPosition(it,viewModel.cartMenus.value.size+2)
            }
        }
    }

    companion object {
        const val TAG = ".CartFragment"
        fun newInstance() = CartFragment()
    }
}