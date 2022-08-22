package com.seom.banchan.ui.base

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.seom.banchan.R
import com.seom.banchan.ui.cart.CartFragment
import com.seom.banchan.ui.main.MainActivity
import com.seom.banchan.ui.recent.RecentFragment

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

    fun addMainMenuProvider(lifecycleOwner: LifecycleOwner) {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.menu_cart)
                    fragmentNavigation.replaceFragment(CartFragment.newInstance(), CartFragment.TAG)
                else if(menuItem.itemId == R.id.menu_order_list){

                }
                return true
            }
        }, lifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun addOrderMenuProvider(lifecycleOwner: LifecycleOwner) {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_order,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.menu_order_list){

                }
                return true
            }
        }, lifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.let{
            when(fragmentNavigation.getCurrentFragment()){
                is CartFragment ->{
                    it.setTitle(R.string.cart_title)
                    it.setDisplayHomeAsUpEnabled(true)
                }
                is RecentFragment -> {
                    it.setTitle(R.string.recent_title)
                    it.setDisplayHomeAsUpEnabled(true)
                }
                else -> {
                    it.setTitle(R.string.main_title)
                    it.setDisplayHomeAsUpEnabled(false)
                }
            }
        }
    }

}