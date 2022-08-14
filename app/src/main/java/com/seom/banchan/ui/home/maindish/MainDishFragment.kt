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
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.defaultSortItems
import com.seom.banchan.ui.model.home.FilterMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
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
        viewModel.fetchSortedMainMenus(0)
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.mainDishUiState.collect {
                homeAdapter.submitList(
                    listOf(
                        HeaderMenuModel(id = getString(R.string.header_view_holder), title = R.string.header_main),
                        FilterMenuModel(
                            id = getString(R.string.filter_view_holder),
                            togglePosition = viewModel.toggleState.value.selectedTogglePosition,
                            onToggle = { it ->
                                viewModel.updateToggle(it)
                            },
                            position = viewModel.mainDishUiState.value.selectedSortPosition,
                            sortByItems = viewModel.mainDishUiState.value.defaultSortItems,
                            onSort = {
                                viewModel.fetchSortedMainMenus(position = it)
                            }
                        )
                    ) + it.mainMenus
                )
            }
        }

        lifecycleScope.launch {
            viewModel.toggleState.collect {
                viewModel.updateViewMode(it.viewModeToggle)
            }
        }
    }

    private fun initRecyclerView() = binding?.let {
        it.rvMainDish.run {
            adapter = homeAdapter
            setGridLayoutManager(requireContext())
        }
    }

    companion object {
        const val TAG = ".MainDishFragment"
        fun newInstance() = MainDishFragment()
    }

}