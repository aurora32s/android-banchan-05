package com.seom.banchan.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentCartBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.NoneDataUiModel
import com.seom.banchan.ui.model.cart.CartCheckModel
import com.seom.banchan.ui.model.cart.CartMenuUiModel
import com.seom.banchan.ui.model.cart.CartOrderModel
import com.seom.banchan.ui.model.cart.CartRecentModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.model.order.OrderInfoModel
import com.seom.banchan.ui.order.detail.OrderDetailFragment
import com.seom.banchan.ui.recent.RecentFragment
import com.seom.banchan.ui.view.dialog.CartMenuCountAlert
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.ext.toast
import com.seom.banchan.util.listener.ModelAdapterListener
import com.seom.banchan.util.provider.impl.ResourceProviderImpl
import com.seom.banchan.worker.alarm.DeliveryAlarmManager
import com.seom.banchan.worker.model.DeliveryAlarmModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BaseFragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding

    private val viewModel: CartViewModel by viewModels()

    private val cartAdapter: ModelRecyclerAdapter<Model> by lazy {
        ModelRecyclerAdapter(
            resourceProvider = ResourceProviderImpl(requireContext()),
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
                            val cartMenu = model as CartMenuUiModel
                            when (view.id) {
                                R.id.cb_menu -> viewModel.updateCheck(cartMenu)
                                R.id.iv_delete -> viewModel.removeItem(cartMenu)
                                R.id.iv_up -> viewModel.increaseCount(cartMenu)
                                R.id.iv_down -> viewModel.decreaseCount(cartMenu)
                                R.id.tv_count -> {
                                    CartMenuCountAlert.build(cartMenu.count)
                                        .setOnClickMoveToCart {
                                            viewModel.updateCount(model, it)
                                        }
                                        .show(parentFragmentManager)
                                }
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
                        CellType.CART_MENU_RECENT_CELL -> {
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
        it.rvCart.itemAnimator = null
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
        repeatLaunch {
            launch { // ????????? ?????? ??????
                viewModel.cartMenus.collectLatest {
                    if (it.isEmpty()) {
                        cartAdapter.updateModelsAtPosition(listOf(NoneDataUiModel()), 1, 2)
                    } else {
                        cartAdapter.updateModelsAtPosition(it, 1, it.size + 1)
                    }
                }
            }
            launch { // ?????? ??????(?????? ??????, ?????????, ??? ?????? ??????)
                viewModel.orderInfo.collectLatest {
                    val cartSize =
                        if (viewModel.cartMenus.value.isEmpty()) 1 else viewModel.cartMenus.value.size
                    cartAdapter.updateModelAtPosition(it, cartSize + 1)
                }
            }
            launch { // ???????????? ??????
                viewModel.cartOrder.collectLatest {
                    val cartSize =
                        if (viewModel.cartMenus.value.isEmpty()) 1 else viewModel.cartMenus.value.size
                    cartAdapter.updateModelAtPosition(it, cartSize + 2)
                }
            }
            launch { // ??????
                viewModel.cartCheck.collectLatest {
                    cartAdapter.updateModelAtPosition(it, 0)
                }
            }
            launch { // ?????? ??? ??????
                viewModel.cartRecent.collectLatest {
                    val cartSize =
                        if (viewModel.cartMenus.value.isEmpty()) 1 else viewModel.cartMenus.value.size
                    cartAdapter.updateModelAtPosition(it, cartSize + 3)
                }
            }
            launch {
                viewModel.cartUiState.collectLatest {
                    when (it) {
                        is CartUiStateModel.SuccessOrder -> {
                            // ?????? ??????
                            DeliveryAlarmManager.create(
                                requireContext(),
                                it.deliveryAlarmModel
                            )
                            fragmentNavigation.popStack()
                            fragmentNavigation.replaceFragment(
                                OrderDetailFragment.newInstance(it.deliveryAlarmModel.orderId),
                                OrderDetailFragment.TAG
                            )
                        }
                        CartUiStateModel.FailToAddCart -> {
                            requireContext().toast(getString(R.string.fail_to_add_order))
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