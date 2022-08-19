package com.seom.banchan.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seom.banchan.databinding.FragmentOrderListBinding
import com.seom.banchan.ui.base.BaseFragment

class OrderListFragment : BaseFragment() {

    private var _binding: FragmentOrderListBinding? = null
    private val binding = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    companion object {
        const val TAG = ".OrderListFragment"
        fun newInstance() = OrderListFragment()
    }
}

val mockData = (0..10).map {

}