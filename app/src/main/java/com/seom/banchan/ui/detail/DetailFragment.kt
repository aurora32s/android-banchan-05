package com.seom.banchan.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.databinding.FragmentDetailBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.util.ext.fromDpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment: Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.detailMenuModel.collect {
                when (it) {
                    is DetailUiState.Success -> {
                        println(it.detailMenu)
                        binding?.detail = it.detailMenu
                        initRecyclerView(it.detailMenu)
                        initAppbar()
                    }
                    DetailUiState.UnInitialized -> {
                        viewModel.fetchData("HBDEF")
                    }
                }
            }
        }
    }

    private fun initRecyclerView(detailMenuUiModel: DetailMenuUiModel) = binding?.let {
        var detailItem = listOf<Model>(detailMenuUiModel)
        detailMenuUiModel.detailMenu.detailImages?.let { image ->
            detailItem += image.map {
                ImageSliderModel(imageUrl = it, type = CellType.IMAGE_LIST_CELL)
            }
        }

        it.rvMenuInfo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val menuDetailAdapter = ModelRecyclerAdapter<Model>()
        it.rvMenuInfo.adapter = menuDetailAdapter
        menuDetailAdapter.submitList(detailItem)
    }

    private fun initAppbar() = binding?.let {
        var totalScroll = 0F
        it.rvMenuInfo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalScroll += dy

                val topPadding = 300f.fromDpToPx().toFloat()
                if (totalScroll > topPadding) {
                    it.toolbar.alpha = 1f
                } else {
                    it.toolbar.alpha = 0f
                }
            }
        })
    }

    companion object {
        const val TAG = ".DetailFragment"
        fun newInstance() = DetailFragment()
    }
}