package com.seom.banchan.ui.home.sidedish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentSideDishBinding
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.home.CartBottomSheetManager
import com.seom.banchan.ui.home.soupdish.SoupDishUiState
import com.seom.banchan.ui.model.*
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.model.home.SortMenuModel
import com.seom.banchan.ui.model.home.TotalMenuModel
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.ext.setGridLayoutManager
import com.seom.banchan.util.ext.setIconDrawable
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SideDishFragment : BaseFragment() {
    private var _binding: FragmentSideDishBinding? = null
    private val binding get() = _binding

    private val viewModel: SideDishViewModel by viewModels()

    private val homeAdapter: ModelRecyclerAdapter<Model> by lazy {
        ModelRecyclerAdapter(
            modelAdapterListener =
            object : ModelAdapterListener {
                override fun onClick(view: View, model: Model, position: Int) {
                    when (model.type) {
                        CellType.MENU_CELL -> {
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
                            }
                        }
                        else -> {}
                    }
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideDishBinding.inflate(inflater)

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
        if (viewModel.sideDishUiState.value == SideDishUiState.FailFetchMenus) {
            viewModel.fetchSortedSideMenus(SortItem.BASE)
        }
    }

    private fun initObserver() {
        repeatLaunch {
            launch {
                viewModel.sideMenus.collect {
                    homeAdapter.updateList(it, getDefaultHeaders().size)
                    homeAdapter.updateModelAtPosition(
                        TotalMenuModel(
                            id = ModelId.TOTAL.name,
                            count = it.size, // 따로 구현
                        ),
                        1
                    )
                }
            }
            launch {
                viewModel.sideDishUiState.collect {
                    when (it) {
                        SideDishUiState.FailFetchMenus -> handleFailFetchMenus()
                        SideDishUiState.SuccessFetchMenus -> handleSuccess()
                        SideDishUiState.UnInitialized -> viewModel.fetchSortedSideMenus(SortItem.BASE)
                    }
                }
            }
        }
    }

    /**
     * 데이터를 받아오는 도중 문제 발생
     */
    private fun handleFailFetchMenus() = binding?.let {
        it.rvSideDish.isGone = true
        it.grWarn.isVisible = true
        it.ivWarnIcon.setIconDrawable(R.drawable.ic_error, true)
    }

    private fun handleSuccess() = binding?.let {
        it.rvSideDish.isVisible = true
        it.grWarn.isGone = true
    }

    private fun initRecyclerView() = binding?.let {
        it.rvSideDish.adapter = homeAdapter
        it.rvSideDish.setGridLayoutManager(requireContext())
        it.rvSideDish.addItemDecoration(
            GridItemDecoration(
                requireContext(),
                true,
                noneApplyIndex = 3
            )
        )
        homeAdapter.submitList(
            getDefaultHeaders()
        )
    }

    private fun initViews() = binding?.let {
        it.btnReRequest.setOnClickListener { viewModel.fetchSortedSideMenus(SortItem.BASE) }
    }

    private fun getDefaultHeaders() = listOf(
        HeaderMenuModel(
            id = ModelId.HEADER.name,
            title = R.string.header_side
        ),
        TotalMenuModel(
            id = ModelId.TOTAL.name,
            count = viewModel.sideMenus.value.size
        ),
        SortMenuModel(
            id = ModelId.SORT.name,
            sortItems = defaultSortItems(),
            onSort = { sortItem ->
                viewModel.fetchSortedSideMenus(sortItem)
            }
        )
    )

    private fun showCartBottomSheetDialog(menuModel: HomeMenuModel) {
        (parentFragment as? CartBottomSheetManager)?.showBottomSheet(menuModel)
    }

    companion object {
        const val TAG = ".SideDishFragment"
        fun newInstance() = SideDishFragment()
    }

}