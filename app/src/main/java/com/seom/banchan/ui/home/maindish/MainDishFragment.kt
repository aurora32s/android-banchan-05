package com.seom.banchan.ui.home.maindish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.FragmentMainDishBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
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
        viewModel.fetchMainMenus()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.mainDishMenus.collect {
                homeAdapter.submitList(it)
            }

        }
        lifecycleScope.launch {
            viewModel.toggle.collect{
                if(!it) changeGridLayoutManager() else changeLinearLayoutManager()
                viewModel.updateViewMode()
            }
        }
    }

    private fun initRecyclerView() = binding?.let {
        it.rvMainDish.adapter = homeAdapter
    }

    private fun changeGridLayoutManager() {
        binding?.let {
            val layoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (it.rvMainDish.adapter?.getItemViewType(position)) {
                        CellType.MENU_GRID_CELL.ordinal -> 1
                        else -> 2
                    }
                }
            }

            it.rvMainDish.layoutManager = layoutManager
        }

    }

    private fun changeLinearLayoutManager() {
        binding?.let {
            it.rvMainDish.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
        const val TAG = ".MainDishFragment"
        fun newInstance() = MainDishFragment()
    }

}