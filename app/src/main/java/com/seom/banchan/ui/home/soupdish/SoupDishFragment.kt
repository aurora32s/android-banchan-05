package com.seom.banchan.ui.home.soupdish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentSoupDishBinding
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.home.CartBottomSheetManager
import com.seom.banchan.ui.home.maindish.MainDishUiState
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
class SoupDishFragment : BaseFragment() {
    private var _binding: FragmentSoupDishBinding? = null
    private val binding get() = _binding

    private val viewModel: SoupDishViewModel by viewModels()

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
        _binding = FragmentSoupDishBinding.inflate(inflater)
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
        if (viewModel.soupDishUiState.value == SoupDishUiState.FailFetchMenus) {
            viewModel.fetchSortedSoupMenus()
        }
    }

    private fun initObserver() {
        repeatLaunch {
            launch {
                viewModel.soupMenus.collect {
                    homeAdapter.updateList(it, getDefaultHeaders().size)
                    homeAdapter.updateModelAtPosition(
                        TotalMenuModel(
                            id = ModelId.TOTAL.name,
                            count = it.size
                        ),
                        1
                    )
                }
            }
            launch {
                viewModel.soupDishUiState.collect {
                    when (it) {
                        SoupDishUiState.FailFetchMenus -> handleFailFetchMenus()
                        SoupDishUiState.SuccessFetchMenus -> handleSuccess()
                        SoupDishUiState.UnInitialized -> viewModel.fetchSortedSoupMenus()
                    }
                }
            }
        }
    }

    /**
     * ???????????? ???????????? ?????? ?????? ??????
     */
    private fun handleFailFetchMenus() = binding?.let {
        it.rvSoupDish.isGone = true
        it.grWarn.isVisible = true
        it.ivWarnIcon.setIconDrawable(R.drawable.ic_error, true)
    }

    private fun handleSuccess() = binding?.let {
        it.rvSoupDish.isVisible = true
        it.grWarn.isGone = true
    }

    private fun initRecyclerView() = binding?.let {
        it.rvSoupDish.adapter = homeAdapter
        it.rvSoupDish.setGridLayoutManager(requireContext())
        it.rvSoupDish.addItemDecoration(
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
        it.btnReRequest.setOnClickListener { viewModel.fetchSortedSoupMenus() }
    }

    private fun getDefaultHeaders() = listOf(
        HeaderMenuModel(
            id = ModelId.HEADER.name,
            title = R.string.header_soup
        ),
        TotalMenuModel(
            id = ModelId.TOTAL.name,
            count = viewModel.soupMenus.value.size
        ),
        SortMenuModel(
            id = ModelId.SORT.name,
            onSort = { sortItem ->
                viewModel.updateSort(sortItem)
                viewModel.fetchSortedSoupMenus()
            },
            sortState = viewModel.sortState
        )
    )

    private fun showCartBottomSheetDialog(menuModel: HomeMenuModel) {
        (parentFragment as? CartBottomSheetManager)?.showBottomSheet(menuModel)
    }

    companion object {
        const val TAG = ".SoupDishFragment"
        fun newInstance() = SoupDishFragment()
    }

}