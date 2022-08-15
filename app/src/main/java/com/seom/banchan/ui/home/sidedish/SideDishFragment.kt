package com.seom.banchan.ui.home.sidedish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentSideDishBinding
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.defaultSortItems
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.TotalMenuModel
import com.seom.banchan.util.ext.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SideDishFragment : Fragment() {
    private var _binding: FragmentSideDishBinding? = null
    private val binding get() = _binding

    private val viewModel: SideDishViewModel by viewModels()

    private val homeAdapter: ModelRecyclerAdapter<Model> by lazy { ModelRecyclerAdapter() }

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
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.sideDishUiState.collect {
                homeAdapter.submitList(listOf(
                    HeaderMenuModel(
                        id = getString(R.string.header_view_holder),
                        title = R.string.header_side
                    ),
                    TotalMenuModel(
                        id = getString(R.string.total_view_holder),
                        count = it.sideMenus.size,
                        position = viewModel.sideDishUiState.value.selectedSortPosition,
                        sortByItems = viewModel.sideDishUiState.value.defaultSortItems,
                        onSort = { position ->
                            viewModel.fetchSortedSideMenus(position)
                        }
                    )
                ) + it.sideMenus)
            }
        }
    }

    private fun initRecyclerView() = binding?.let {
        it.rvSideDish.adapter = homeAdapter
        it.rvSideDish.setGridLayoutManager(requireContext())
        it.rvSideDish.addItemDecoration(GridItemDecoration(requireContext(),true).decoration)
    }

    companion object {
        const val TAG = ".SideDishFragment"
        fun newInstance() = SideDishFragment()
    }

}