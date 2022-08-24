package com.seom.banchan.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentDetailBinding
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.home.HomeFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.detail.DetailBottomButtonModel
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.detail.MenuCountModel
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.ui.view.dialog.DetailAddCartAlert
import com.seom.banchan.util.ext.fromDpToPx
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    private val viewModel: DetailViewModel by viewModels()

    private val menuDetailAdapter: ModelRecyclerAdapter<Model> by lazy {
        ModelRecyclerAdapter(
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
                        CellType.DETAIL_BOTTOM_BUTTON_CELL -> {
                            // 장바구니 추가 버튼 클릭
                            viewModel.addMenuToCart()
                        }
                        else -> {}
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        // backstack 으로 돌아왔을 때 UiState 상태 값이 마지막 상태에 머물러 있어
        // onCreateView 에서 UiState 를 초기 값으로 변경
        viewModel.init()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar(viewLifecycleOwner,viewModel.cartMenus,viewModel.orderList)
        val menu: MenuModel? = arguments?.getSerializable(KEY_MODEL_BUNDLE) as? MenuModel
        lifecycleScope.launch {
            viewModel.detailMenuModel.collect {
                when (it) {
                    is DetailUiState.Success.SuccessFetch -> {
                        binding?.detail = it.detailMenu
                        initRecyclerView(it.detailMenu)
                        initAppbar()
                    }
                    DetailUiState.UnInitialized -> {
                        viewModel.fetchData(menu)
                    }
                    is DetailUiState.Success.SuccessAddToCart -> {
                        showDialog()
                    }
                }
            }
        }
    }

    private fun initRecyclerView(detailMenuUiModel: DetailMenuUiModel) = binding?.let {
        var detailItem = listOf(
            detailMenuUiModel,
            MenuCountModel(id = "menuCount", count = viewModel.count),
            DetailBottomButtonModel(id = "bottomButton", totalCount = viewModel.totalPrice)
        )
        detailMenuUiModel.detailMenu.detailImages?.let { image ->
            detailItem += image.map {
                ImageSliderModel(imageUrl = it, type = CellType.IMAGE_LIST_CELL)
            }
        }

        it.rvMenuInfo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

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

    private fun showDialog() {
        // 장바구니 추가 성공 시
        DetailAddCartAlert.build()
            .setOnClickMoveToCart {
                // TODO 일단 이전화면으로 이동하도록 해두었습니다. 장바구니 화면 완성후 이 부분을 수정하면 될 듯합니다.
                fragmentNavigation.replaceFragment(
                    HomeFragment.newInstance(),
                    HomeFragment.TAG
                )
            }
            .show(childFragmentManager)
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