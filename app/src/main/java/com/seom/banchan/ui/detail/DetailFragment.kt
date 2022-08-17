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
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentDetailBinding
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.detail.MenuCountModel
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.util.ext.fromDpToPx
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
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

        val menu: MenuModel? = arguments?.getSerializable(KEY_MODEL_BUNDLE) as? MenuModel
        lifecycleScope.launch {
            viewModel.detailMenuModel.collect {
                when (it) {
                    is DetailUiState.Success -> {
                        binding?.detail = it.detailMenu
                        initRecyclerView(it.detailMenu)
                        initAppbar()
                    }
                    DetailUiState.UnInitialized -> {
                        viewModel.fetchData(menu)
                    }
                }
            }
        }
    }

    private fun initRecyclerView(detailMenuUiModel: DetailMenuUiModel) = binding?.let {
        var detailItem = listOf(
            detailMenuUiModel,
            MenuCountModel(id = "menuCount", count = viewModel.count)
        )
        detailMenuUiModel.detailMenu.detailImages?.let { image ->
            detailItem += image.map {
                ImageSliderModel(imageUrl = it, type = CellType.IMAGE_LIST_CELL)
            }
        }

        it.rvMenuInfo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val menuDetailAdapter = ModelRecyclerAdapter<Model>(
            modelAdapterListener = object : ModelAdapterListener {
                override fun onClick(view: View, model: Model, position: Int) {
                    when (model.type) {
                        CellType.DETAIL_COUNT_CELL -> {
                            if (view.id == R.id.iv_up_count) {
                                viewModel.increaseCount()
                            } else if (view.id == R.id.iv_down_count) {
                                viewModel.decreaseCount()
                            }
                        }
                        else -> {}
                    }
                }
            }
        )
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
        const val KEY_MODEL_BUNDLE = "SelectedModel"

        fun newInstance(menuModel: MenuModel): DetailFragment {
            val instance = DetailFragment()

            val bundle = Bundle()
            bundle.putSerializable(KEY_MODEL_BUNDLE, menuModel)
            instance.arguments = bundle

            return instance
        }
    }
}