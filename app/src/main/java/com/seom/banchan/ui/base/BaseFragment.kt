package com.seom.banchan.ui.base

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.seom.banchan.R
import com.seom.banchan.domain.model.cart.CartMenuModel
import com.seom.banchan.domain.model.order.OrderListModel
import com.seom.banchan.ui.cart.CartFragment
import com.seom.banchan.ui.model.order.OrderDeliveryState
import com.seom.banchan.ui.order.OrderListFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

open class BaseFragment : Fragment() {
    lateinit var fragmentNavigation: FragmentNavigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) { /// MainActivity 가 FragmentNavigation을 구현하고 있기 때문에
            fragmentNavigation = context   /// fragmentNavigation을 통해 구현한 FragmentNavigation에 접근 가능
        }
    }

    interface FragmentNavigation {
        fun addFragment(fragment: Fragment, fragmentTag: String? = null)
        fun replaceFragment(fragment: Fragment, fragmentTag: String? = null)
        fun popStack()
        fun getCurrentFragment() : Fragment?
    }

    fun addMainMenuProvider(
        lifecycleOwner: LifecycleOwner,
        cartMenusFlow: Flow<List<CartMenuModel>>,
        orderMenusFlow : Flow<List<OrderListModel>>
    ) {
        (requireActivity() as MenuHost).addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_main,menu)

                    val cartActionView = menu.getItem(0).actionView

                    val cartLayout = cartActionView.findViewById<ConstraintLayout>(R.id.cl_cart_action)
                    val cartImageView = cartActionView.findViewById<AppCompatImageView>(R.id.iv_cart_action)
                    val count = cartActionView.findViewById<AppCompatTextView>(R.id.tv_count_action)

                    cartLayout.setOnClickListener {
                        fragmentNavigation.replaceFragment(CartFragment.newInstance(),CartFragment.TAG)
                    }

                    val orderMenu = menu.getItem(1)

                    lifecycleOwner.lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED){
                            launch {
                                cartMenusFlow.collectLatest {
                                    if(it.isNotEmpty()){
                                        count.isVisible = true
                                        count.text = if(it.size<10) it.size.toString() else getString(R.string.appbar_cart_over)
                                        cartImageView.setImageResource(R.drawable.ic_cart_action)
                                    } else {
                                        count.isVisible = false
                                        cartImageView.setImageResource(R.drawable.ic_cart)
                                    }
                                }
                            }
                            launch {
                                orderMenusFlow.collectLatest { list ->
                                    if(list.any {
                                        it.deliveryType == OrderDeliveryState.DELIVERING
                                    }) orderMenu.setIcon(R.drawable.ic_user_badge)
                                    else orderMenu.setIcon(R.drawable.ic_user)
                                }
                            }
                        }
                    }
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    if(menuItem.itemId == R.id.menu_order_list){
                         fragmentNavigation.replaceFragment(OrderListFragment.newInstance(),OrderListFragment.TAG)
                    }
                    return true
                }
            },lifecycleOwner,Lifecycle.State.RESUMED
        )
    }
}