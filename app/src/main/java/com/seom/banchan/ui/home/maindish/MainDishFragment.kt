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
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.ModelId
import com.seom.banchan.ui.model.SortItem
import com.seom.banchan.ui.model.defaultSortItems
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.SortMenuModel
import com.seom.banchan.util.ext.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainDishFragment : Fragment() {
    private var _binding: FragmentMainDishBinding? = null
    private val binding get() = _binding

    private val viewModel: MainDishViewModel by viewModels()

    private val homeAdapter: ModelRecyclerAdapter<Model> by lazy { ModelRecyclerAdapter() }

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
            viewModel.mainDishUiState.collect {
                homeAdapter.updateList(
                    it.mainMenus,getDefaultHeaders().size
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

    private fun setItemDecoration(toggle : Boolean) = binding?.rvMainDish?.let {
        if(it.itemDecorationCount != 0)
            it.removeItemDecorationAt(0)
        it.addItemDecoration(GridItemDecoration(requireContext(),!toggle,3))
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

    companion object {
        const val TAG = ".MainDishFragment"
        fun newInstance() = MainDishFragment()
    }

}