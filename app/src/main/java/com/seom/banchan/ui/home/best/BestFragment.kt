package com.seom.banchan.ui.home.best

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.FragmentBestBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BestFragment : BaseFragment() {
    private var _binding: FragmentBestBinding? = null
    private val binding get() = _binding

    private val viewModel: BestViewModel by viewModels()

    private lateinit var homeAdapter: ModelRecyclerAdapter<Model>

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

        initObserver()
        viewModel.fetchBestMenus()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.bestMenus.collect {
                homeAdapter.submitList(it)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initRecyclerView() = binding?.let {
        it.rvBest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (::homeAdapter.isInitialized.not()) {
            homeAdapter = ModelRecyclerAdapter(
                modelAdapterListener =
                object : ModelAdapterListener {
                    override fun onClick(model: Model) {
                        when (model.type) {
                            CellType.MENU_CELL -> {
                            } // 메뉴 아이템 클릭
                            else -> {}
                        }
                    }
                })
        }
        it.rvBest.adapter = homeAdapter
    }

    companion object {
        const val TAG = ".BestFragment"
        fun newInstance() = BestFragment()
    }

}