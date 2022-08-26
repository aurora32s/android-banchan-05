package com.seom.banchan.util.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.seom.banchan.ui.cart.CartFragment
import com.seom.banchan.ui.order.OrderListFragment

class FragmentNavigationController(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) {

    fun addFragment(fragment: Fragment, fragmentTag: String? = null) {
        fragmentManager.commit {
            add(containerId, fragment, fragmentTag)
        }
    }

    // 필요한 Transaction 추가 ..
    fun replaceFragment(fragment: Fragment, fragmentTag: String? = null) {
        if(fragment is CartFragment){ // 추가할 Fragment가 CartFragment이면 backstack에서 CartFragment를 지운다 -> CartFragment는 하나만!
            fragmentManager.popBackStack(CartFragment.TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        fragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(fragmentTag)
            replace(containerId, fragment, fragmentTag)
        }
    }

    fun popStack() {
        fragmentManager.popBackStack()
    }

    fun getCurrentFragment() : Fragment? {
        return fragmentManager.findFragmentById(containerId)
    }

    /**
     * 한번 compose 의 navigation 처럼 화면 별로 function 을 만들어 보았어요.
     */
    fun moveToOrderListFragment() {
        this.replaceFragment(OrderListFragment.newInstance(), OrderListFragment.TAG)
    }
}