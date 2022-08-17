package com.seom.banchan.ui.home.soupdish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentSoupDishBinding
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.ModelId
import com.seom.banchan.ui.model.defaultSortItems
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.SortMenuModel
import com.seom.banchan.ui.model.home.TotalMenuModel
import com.seom.banchan.util.ext.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SoupDishFragment : Fragment() {
    private var _binding: FragmentSoupDishBinding? = null
    private val binding get() = _binding

    private val viewModel: SoupDishViewModel by viewModels()

    private val homeAdapter: ModelRecyclerAdapter<Model> by lazy { ModelRecyclerAdapter() }

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
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.soupDishUiState.collect {
                homeAdapter.updateList(it.soupMenus,getDefaultHeaders().size)
                homeAdapter.updateModelAtPosition(
                    TotalMenuModel(
                        id = ModelId.TOTAL.name,
                        count = viewModel.soupDishUiState.value.soupMenus.size
                    ),
                    1
                )
            }
        }
    }

    private fun initRecyclerView() = binding?.let {
        it.rvSoupDish.adapter = homeAdapter
        it.rvSoupDish.setGridLayoutManager(requireContext())
        it.rvSoupDish.addItemDecoration(GridItemDecoration(requireContext(),true).decoration)
        homeAdapter.submitList(
            getDefaultHeaders()
        )
    }

    private fun getDefaultHeaders() = listOf(
        HeaderMenuModel(
            id = ModelId.HEADER.name,
            title = R.string.header_soup
        ),
        TotalMenuModel(
            id = ModelId.TOTAL.name,
            count = viewModel.soupDishUiState.value.soupMenus.size
        ),
        SortMenuModel(
            id = ModelId.SORT.name,
            sortItems = defaultSortItems(),
            onSort = { sortItem ->
                viewModel.fetchSortedSoupMenus(sortItem)
            }
        )
    )

    companion object {
        const val TAG = ".SoupDishFragment"
        fun newInstance() = SoupDishFragment()
    }

}