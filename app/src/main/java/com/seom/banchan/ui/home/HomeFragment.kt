package com.seom.banchan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentHomeBinding
import com.seom.banchan.ui.adapter.FragmentPagerAdapter
import com.seom.banchan.ui.home.best.BestFragment
import com.seom.banchan.ui.home.maindish.MainDishFragment
import com.seom.banchan.ui.home.sidedish.SideDishFragment
import com.seom.banchan.ui.home.soupdish.SoupDishFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() = binding?.let {
        val list = listOf(
            BestFragment.newInstance(),
            MainDishFragment.newInstance(),
            SoupDishFragment.newInstance(),
            SideDishFragment.newInstance())

        val pagerAdapter = FragmentPagerAdapter(this@HomeFragment, list)
        val tabList = resources.getStringArray(R.array.tab_list)

        it.vpHome.adapter = pagerAdapter
        it.vpHome.offscreenPageLimit = list.size

        TabLayoutMediator(it.tlHome,it.vpHome) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    companion object {
        const val TAG = ".HomeFragment"
        fun newInstance() = HomeFragment()
    }
}