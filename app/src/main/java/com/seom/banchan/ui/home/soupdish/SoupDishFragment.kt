package com.seom.banchan.ui.home.soupdish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentSoupDishBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HeaderMenuModel
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
        viewModel.fetchSoupMenus()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.soupDishMenus.collect {
                homeAdapter.submitList(
                    listOf(
                        HeaderMenuModel(
                            id = getString(R.string.header_view_holder),
                            title = R.string.header_soup
                        ),
                        TotalMenuModel(
                            id = getString(R.string.total_view_holder),
                            count = it.size
                        )
                    ) + it
                )
            }
        }
    }

    private fun initRecyclerView() = binding?.let {
        it.rvSoupDish.adapter = homeAdapter
        it.rvSoupDish.setGridLayoutManager(requireContext())
    }

    companion object {
        const val TAG = ".SoupDishFragment"
        fun newInstance() = SoupDishFragment()
    }

}