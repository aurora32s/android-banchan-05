package com.seom.banchan.ui.base

import android.content.Context
import androidx.fragment.app.Fragment

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
    }
}