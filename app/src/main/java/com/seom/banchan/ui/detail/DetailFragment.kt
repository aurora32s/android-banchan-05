package com.seom.banchan.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentDetailBinding
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.cart.CartFragment
import com.seom.banchan.ui.home.HomeFragment
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.detail.DetailBottomButtonModel
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.detail.MenuCountModel
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.ui.view.dialog.DetailAddCartAlert
import com.seom.banchan.util.ext.fromDpToPx
import com.seom.banchan.util.ext.repeatLaunch
import com.seom.banchan.util.ext.setIconDrawable
import com.seom.banchan.util.ext.toast
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
                            // ???????????? ?????? ?????? ??????
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
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar(viewLifecycleOwner, viewModel.cartMenus, viewModel.orderList)

        // ??? ???????????? ???????????? ????????? ?????? ??????
        val menu: MenuModel? = arguments?.getSerializable(KEY_MODEL_BUNDLE) as? MenuModel
        if (menu == null) {
            fragmentNavigation.popStack()
            return
        }
        if (viewModel.detailUiState.value != DetailUiState.UnInitialized) {
            viewModel.init()
        }
        initObserve(menu)
        initAppbar()
    }

    private fun initObserve(menu: MenuModel) {
        repeatLaunch {
            launch {
                viewModel.currentMenu.collectLatest {
                    it?.let {
                        binding?.detail = it
                        initRecyclerView(it)
                    }
                }
            }
            launch {
                viewModel.detailUiState.collectLatest {
                    when (it) {
                        DetailUiState.UnInitialized -> viewModel.fetchData(menu)
                        DetailUiState.SuccessAddToCart -> showDialog()
                        DetailUiState.FailFetchDetail -> handleFailFetchDetail()
                        DetailUiState.FailAddToCart -> {
                            requireContext().toast(getString(R.string.fail_to_add_cart))
                        }
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
        // ?????? ?????? ????????????
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

    /**
     * ???????????? scroll ???, ?????? ????????? ????????? ?????? ?????? ??????????????? app bar ??????
     */
    private fun initAppbar() = binding?.let {
        var totalScroll = 0F
        it.rvMenuInfo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalScroll += dy

                val topPadding = 300f.fromDpToPx().toFloat()
                it.toolbar.alpha = if (totalScroll > topPadding) 1f else 0f
            }
        })
    }

    private fun handleFailFetchDetail() = binding?.let {
        it.rvMenuInfo.isGone = true
        it.toolbar.isGone = true
        it.grWarn.isVisible = true
        it.ivWarnIcon.setIconDrawable(R.drawable.ic_error, true)
        it.tvWarnMsg.text = getString(R.string.warn_exception_occur)
    }

    private fun showDialog() {
        // ???????????? ?????? ?????? ???
        DetailAddCartAlert.build()
            .setOnClickMoveToCart {
                fragmentNavigation.replaceFragment(
                    CartFragment.newInstance(),
                    CartFragment.TAG
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