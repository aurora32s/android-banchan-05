package com.seom.banchan.ui.home.maindish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentMainDishBinding
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.home.CartBottomSheetManager
import com.seom.banchan.ui.model.*
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.model.home.SortMenuModel
import com.seom.banchan.util.ext.setGridLayoutManager
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainDishFragment : BaseFragment() {
    private var _binding: FragmentMainDishBinding? = null
    private val binding get() = _binding

    private val viewModel: MainDishViewModel by viewModels()

    private val homeAdapter: ModelRecyclerAdapter<Model> by lazy {
        ModelRecyclerAdapter(modelAdapterListener =
        object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {
                println(model.type)
                when (model.type) {
                    CellType.MENU_CELL, CellType.MENU_LARGE_CELL -> {
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
        _binding = FragmentMainDishBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()
        viewModel.fetchSortedMainMenus(SortItem.BASE)
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.mainMenus.collect {
                homeAdapter.updateList(
                    it, getDefaultHeaders().size
                )
            }
        }

        lifecycleScope.launch {
            viewModel.toggleState.collect {
                setItemDecoration(it == ToggleState.LINEAR)
            }
        }
    }

    private fun initRecyclerView() = binding?.let {
        it.rvMainDish.run {
            adapter = homeAdapter
            setGridLayoutManager(requireContext())
        }
        homeAdapter.submitList(
            getDefaultHeaders()
        )
    }

    private fun setItemDecoration(toggle: Boolean) = binding?.rvMainDish?.let {
        if (it.itemDecorationCount != 0)
            it.removeItemDecorationAt(0)
        it.addItemDecoration(GridItemDecoration(requireContext(), !toggle, noneApplyIndex = 3))
    }

    private fun getDefaultHeaders() = listOf(
        HeaderMenuModel(id = ModelId.HEADER.name, title = R.string.header_main),
        FilterMenuModel(
            id = ModelId.FILTER.name,
            onToggle = { it ->
                viewModel.updateToggle(it)
            },
        ),
        SortMenuModel(
            id = ModelId.SORT.name,
            sortItems = defaultSortItems(),
            onSort = { sortItem ->
                viewModel.fetchSortedMainMenus(sortItem)
            }
        )
    )

    private fun showCartBottomSheetDialog(menuModel: HomeMenuModel) {
        (parentFragment as? CartBottomSheetManager)?.showBottomSheet(menuModel)
    }

    companion object {
        const val TAG = ".MainDishFragment"
        fun newInstance() = MainDishFragment()
    }

}