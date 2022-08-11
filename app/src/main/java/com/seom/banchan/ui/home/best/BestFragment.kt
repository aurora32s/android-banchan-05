package com.seom.banchan.ui.home.best

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.FragmentBestBinding
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BestFragment : Fragment() {
    private var _binding: FragmentBestBinding? = null
    private val binding get() = _binding

    private val viewModel: BestViewModel by viewModels()

    private val homeAdapter: ModelRecyclerAdapter<Model> by lazy { ModelRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBestBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.bestMenus.observe(viewLifecycleOwner) {
            println(it)
        }

        viewModel.fetchBestMenus()
    }

    private fun initRecyclerView() = binding?.let {
        it.rvBest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        it.rvBest.adapter = homeAdapter
    }

    companion object {
        const val TAG = ".BestFragment"
        fun newInstance() = BestFragment()
    }

}