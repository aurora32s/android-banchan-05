package com.seom.banchan.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentCartBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.ui.model.cart.CartMenuUiModel
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import com.seom.banchan.ui.order.detail.OrderDetailFragment
import com.seom.banchan.ui.recent.RecentFragment
import com.seom.banchan.ui.view.dialog.CartMenuCountAlert
import com.seom.banchan.util.listener.ModelAdapterListener
import com.seom.banchan.worker.alarm.DeliveryAlarmManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BaseFragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding

    @Inject lateinit var deliveryAlarmManager: DeliveryAlarmManager
    private val viewModel: CartViewModel by viewModels()

    private val cartAdapter: ModelRecyclerAdapter<Model> by lazy {
        ModelRecyclerAdapter(
            modelAdapterListener = object : ModelAdapterListener {
                override fun onClick(view: View, model: Model, position: Int) {
                    when (model.type) {
                        CellType.CART_CHECK_CELL -> {
                            if (view.id == R.id.cb_all || view.id == R.id.tv_all) {
                                viewModel.updateAllCheck()
                            } else if (view.id == R.id.tv_delete) {
                                viewModel.removeItems()
                            }
                        }
                        CellType.CART_MENU_CELL -> {
                            if (view.id == R.id.cb_menu) {
                                viewModel.updateCheck((model as CartMenuUiModel))
                            } else if (view.id == R.id.iv_delete) {
                                viewModel.removeItem((model as CartMenuUiModel))
                            } else if (view.id == R.id.iv_up) {
                                viewModel.increaseCount((model as CartMenuUiModel))
                            } else if (view.id == R.id.iv_down) {
                                viewModel.decreaseCount((model as CartMenuUiModel))
                            } else if (view.id == R.id.tv_count) {
                                CartMenuCountAlert.build((model as CartMenuUiModel).count)
                                    .setOnClickMoveToCart {
                                        viewModel.updateCount(model, it)
                                    }
                                    .show(parentFragmentManager)
                            }
                        }
                        CellType.CART_ORDER_CELL -> {
                            if (view.id == R.id.bt_order) {
                                viewModel.insertOrderAndRemoveCartMenus()
                            }
                        }
                        CellType.CART_RECENT_CELL -> {
                            if (view.id == R.id.tv_all) {
                                fragmentNavigation.replaceFragment(
                                    RecentFragment.newInstance(),
                                    RecentFragment.TAG
                                )
                            }
                        }
                        CellType.MENU_RECENT_CELL -> {
                            if (view.id == R.id.iv_menu_thumbnail) {
                                (model as? HomeMenuModel)?.menu?.let {
                                    fragmentNavigation.replaceFragment(
                                        DetailFragment.newInstance(
                                            menuModel = it
                                        ),
                                        DetailFragment.TAG
                                    )
                                }
                            }
                        }
                        else -> {}
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
            listOf(
                CartCheckModel(),
                OrderInfoModel(),
                CartOrderModel(
                    totalPrice = 0,
                ),
                CartRecentModel()
            )
        )
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.cartMenus.collectLatest {
                        cartAdapter.updateModelsAtPosition(it, 1, it.size + 1)
                    }
                }
                launch {
                    viewModel.orderInfo.collectLatest {
                        cartAdapter.updateModelAtPosition(it, viewModel.cartMenus.value.size + 1)
                    }
                }
                launch {
                    viewModel.cartOrder.collectLatest {
                        cartAdapter.updateModelAtPosition(it, viewModel.cartMenus.value.size + 2)
                    }
                }
                launch {
                    viewModel.cartCheck.collectLatest {
                        cartAdapter.updateModelAtPosition(it, 0)
                    }
                }
                launch {
                    viewModel.cartRecent.collectLatest {
                        cartAdapter.updateModelAtPosition(it, viewModel.cartMenus.value.size + 3)
                    }
                }
                launch {
                    viewModel.cartUiEvent.collectLatest {
                        when (it) {
                            is CartUiEventModel.SuccessOrder -> {
                                // 알람 발생
                                deliveryAlarmManager.create(
                                    requireContext(),
                                    it.orderId
                                )
                                fragmentNavigation.popStack()
                                fragmentNavigation.replaceFragment(
                                    OrderDetailFragment.newInstance(it.orderId),
                                    OrderDetailFragment.TAG
                                )
                            }
                            CartUiEventModel.UnInitialized -> {}
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = ".CartFragment"
        fun newInstance() = CartFragment()
    }
}