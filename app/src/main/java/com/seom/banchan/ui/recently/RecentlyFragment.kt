package com.seom.banchan.ui.recently

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.FragmentRecentlyBinding
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.ext.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentlyFragment : BaseFragment() {
    private var _binding: FragmentRecentlyBinding? = null
    private val binding get() = _binding

    private val recentlyAdapter: ModelRecyclerAdapter<Model> by lazy { ModelRecyclerAdapter() }

    private val viewModel: RecentlyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentlyBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()

        viewModel.getRecentlyMenus()
    }

    private fun initRecyclerView() = binding?.let {
        it.rvRecently.adapter = recentlyAdapter
        it.rvRecently.setGridLayoutManager(requireContext())
    }

    private fun initObserver(){
        lifecycleScope.launch{
            viewModel.recentlyMenus.collectLatest {
                recentlyAdapter.submitList(it)
            }
        }
    }
}
