package com.seom.banchan.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seom.banchan.R
import com.seom.banchan.databinding.ActivityMainBinding
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.home.HomeFragment
import com.seom.banchan.util.navigation.FragmentNavigationController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseFragment.FragmentNavigation {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding

    lateinit var fragmentNavigationController : FragmentNavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding?.let {
            it.lifecycleOwner = this
        }

        initToolbar()
        initContainer()
    }

    private fun initToolbar() = binding?.let {
        setSupportActionBar(it.tbMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        it.tbMain.title = getString(R.string.main_title)
    }

    private fun initContainer() = binding?.let {
        fragmentNavigationController = FragmentNavigationController(supportFragmentManager,it.flMain.id)
        fragmentNavigationController.addFragment(HomeFragment.newInstance(),HomeFragment.TAG)
    }

    override fun addFragment(fragment: Fragment, fragmentTag: String?) {
        fragmentNavigationController.addFragment(fragment,fragmentTag)
    }
}