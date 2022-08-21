package com.seom.banchan.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seom.banchan.databinding.FragmentRecentBinding
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.Model
import com.seom.banchan.util.ext.setGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentFragment : BaseFragment() {
    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding

    private val recentldapter: ModelRecyclerAdapter<Model> by lazy { ModelRecyclerAdapter() }

    private val viewModel: RecentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()

        viewModel.getRecentMenus()

    }

    private fun initRecyclerView() = binding?.let {
        it.rvRecent.adapter = recentldapter
        it.rvRecent.setGridLayoutManager(requireContext())
        it.rvRecent.addItemDecoration(GridItemDecoration(requireContext(),true,noneApplyIndex =0))
    }

    private fun initObserver(){
        lifecycleScope.launch{
            viewModel.recentMenus.collectLatest {
                recentldapter.submitList(it)
            }
        }
    }

    companion object {
        const val TAG = ".RecentFragment"
        fun newInstance() = RecentFragment()
    }
}
