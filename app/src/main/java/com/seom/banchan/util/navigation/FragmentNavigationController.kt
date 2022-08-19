package com.seom.banchan.util.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.seom.banchan.ui.order.OrderListFragment

class FragmentNavigationController(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) {
    fun addFragment(fragment: Fragment, fragmentTag: String? = null) {
        fragmentManager.beginTransaction().add(containerId, fragment, fragmentTag).commit()
    }

    // 필요한 Transaction 추가 ..
    fun replaceFragment(fragment: Fragment, fragmentTag: String? = null) {
        fragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(fragmentTag)
            replace(containerId, fragment, fragmentTag)
        }
    }

    fun popStack() {
        fragmentManager.popBackStack()
    }

    /**
     * 한번 compose 의 navigation 처럼 화면 별로 function 을 만들어 보았어요.
     */
    fun moveToOrderListFragment() {
        this.replaceFragment(OrderListFragment.newInstance(), OrderListFragment.TAG)
    }
}