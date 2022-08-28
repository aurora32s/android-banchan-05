package com.seom.banchan.ui.home.best

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentBestBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.home.CartBottomSheetManager
import com.seom.banchan.ui.home.HomeFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.ext.setIconDrawable
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class BestFragment : BaseFragment() {
    private var _binding: FragmentBestBinding? = null
    private val binding get() = _binding

    private val viewModel: BestViewModel by viewModels()

    private val homeAdapter: ModelRecyclerAdapter<Model> by lazy {
        ModelRecyclerAdapter(
            modelAdapterListener =
            object : ModelAdapterListener {
                override fun onClick(view: View, model: Model, position: Int) {
                    when (model.type) {
                        CellType.MENU_BEST_CELL -> {
                            if (view.id == R.id.iv_menu_thumbnail) {
                                (model as? HomeMenuModel)?.menu?.let {
                                    fragmentNavigation.replaceFragment(
                                        DetailFragment.newInstance(
                                            menuModel = it
                                        ),
                                        DetailFragment.TAG
                                    )
                                }
                            } else if (view.id == R.id.iv_cart) {
                                showCartBottomSheetDialog((model as HomeMenuModel))
                                viewModel.updateSelectedCart(model)
                            }
                        } // 메뉴 아이템 클릭
                        else -> {}
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBestBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        initObserver()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        /**
         * 다시 해당 page 로 돌아왔을 때 데이터 요청 상태라면 재요청 시도
         */
        if (viewModel.bestUiState.value == BestUiState.FailFetchMenus) {
            viewModel.fetchBestMenus()
        }
    }

    private fun initObserver() {
        repeatLaunch {
            launch {
                viewModel.bestMenus.collect {
                    homeAdapter.updateList(it,1,viewModel.selectedCart)
                }
            }
            launch {
                viewModel.bestUiState.collect {
                    when (it) {
                        BestUiState.FailFetchMenus -> handleFailFetchMenus()
                        BestUiState.UnInitialized -> viewModel.fetchBestMenus()
                        BestUiState.SuccessFetchMenus -> handleSuccess()
                    }
                }
            }
        }
    }

    /**
     * 데이터를 받아오는 도중 문제 발생
     */
    private fun handleFailFetchMenus() = binding?.let {
        it.rvBest.isGone = true
        it.grWarn.isVisible = true
        it.ivWarnIcon.setIconDrawable(R.drawable.ic_error, true)
    }

    private fun handleSuccess() = binding?.let {
        it.rvBest.isVisible = true
        it.grWarn.isGone = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initRecyclerView() = binding?.let {
        it.rvBest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        it.rvBest.adapter = homeAdapter
        homeAdapter.submitList(
            viewModel.baseMenu
        )
    }

    private fun initViews() = binding?.let {
        it.btnReRequest.setOnClickListener { viewModel.fetchBestMenus() }
    }

    private fun showCartBottomSheetDialog(menuModel: HomeMenuModel) {
        (parentFragment as? CartBottomSheetManager)?.showBottomSheet(menuModel)
    }

    companion object {
        const val TAG = ".BestFragment"
        fun newInstance() = BestFragment()
    }

}