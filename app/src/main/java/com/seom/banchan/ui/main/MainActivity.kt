package com.seom.banchan.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.seom.banchan.R
import com.seom.banchan.databinding.ActivityMainBinding
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.cart.CartFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.home.HomeFragment
import com.seom.banchan.ui.order.OrderListFragment
import com.seom.banchan.ui.order.detail.OrderDetailFragment
import com.seom.banchan.ui.recent.RecentFragment
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.navigation.FragmentNavigationController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseFragment.FragmentNavigation,
    FragmentManager.OnBackStackChangedListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewModel: MainViewModel by viewModels()

    lateinit var fragmentNavigationController: FragmentNavigationController

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
        initObserve()
    }

    private fun initObserve() {
        repeatLaunch {
            launch {
                viewModel.mainUiState.collectLatest {
                    when (it) {
                        MainUiState.UnInitialized -> {
                            initFragment()
                            viewModel.setLoadSuccess()
                        }
                        MainUiState.SuccessLoad -> {}
                    }
                }
            }
        }
    }

    private fun initToolbar() = binding?.let {
        setSupportActionBar(it.tbMain)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
    }

    private fun initContainer() = binding?.let {
        fragmentNavigationController =
            FragmentNavigationController(supportFragmentManager, it.flMain.id)
        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    private fun initFragment() {
        val orderId = intent?.getLongExtra(KEY_ORDER_ID, -1L) ?: -1L
        /**
         * order Id 가 전달된 경우에는 주문 내역 상세 화면으로 이동
         */
        if (orderId >= 0) {
            fragmentNavigationController.replaceFragment(
                OrderDetailFragment.newInstance(orderId),
                OrderDetailFragment.TAG
            )
        } else {
            // 처음 MainActivity 에 들어온 경우에는 홈 화면으로 이동
            replaceFragment(HomeFragment.newInstance(), HomeFragment.TAG)
        }
    }

    override fun onBackStackChanged() {
        setActionBar()
    }

    override fun addFragment(fragment: Fragment, fragmentTag: String?) {
        fragmentNavigationController.addFragment(fragment, fragmentTag)
    }

    override fun replaceFragment(fragment: Fragment, fragmentTag: String?) {
        fragmentNavigationController.replaceFragment(fragment, fragmentTag)
    }

    override fun popStack() {
        if (getCurrentFragment() is HomeFragment) {
            finish()
        }
        fragmentNavigationController.popStack()
    }

    override fun onBackPressed() { // 휴대폰 뒤로 가기 -> popStack으로
        popStack()
    }

    override fun getCurrentFragment(): Fragment? {
        return fragmentNavigationController.getCurrentFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            popStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        supportActionBar?.let {
            when (getCurrentFragment()) {
                is CartFragment -> {
                    it.setTitle(R.string.cart_title)
                    it.setDisplayHomeAsUpEnabled(true)
                }
                is RecentFragment -> {
                    it.setTitle(R.string.recent_title)
                    it.setDisplayHomeAsUpEnabled(true)
                }
                is OrderListFragment -> {
                    it.setTitle(R.string.order_list_title)
                    it.setDisplayHomeAsUpEnabled(true)
                }
                is OrderDetailFragment -> {
                    it.setTitle(R.string.blank)
                    it.setDisplayHomeAsUpEnabled(true)
                }
                else -> {
                    it.setTitle(R.string.main_title)
                    it.setDisplayHomeAsUpEnabled(false)
                }
            }
        }
    }

    companion object {
        const val TAG = ".MainActivity"
        const val KEY_ORDER_ID = "order_id"
    }
}