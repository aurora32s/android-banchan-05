package com.seom.banchan.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentRecentBinding
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.cart.CartFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.home.CartBottomSheetManager
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.view.OrderCartBottomSheetManager
import com.seom.banchan.util.ext.setGridLayoutManager
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentFragment : BaseFragment(), CartBottomSheetManager {
    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding

    private val viewModel: RecentViewModel by viewModels()

    private val recentAdapter: ModelRecyclerAdapter<Model> by lazy {
        ModelRecyclerAdapter(modelAdapterListener =
        object : ModelAdapterListener {
            override fun onClick(view: View, model: Model, position: Int) {
                when (model.type) {
                    CellType.MENU_RECENT_CELL -> {
                        if (view.id == R.id.iv_menu_thumbnail) {
                            (model as? HomeMenuModel)?.menu?.let {
                                fragmentNavigation.replaceFragment(
                                    DetailFragment.newInstance(
                                        menuModel = it
                                    ),
                                    DetailFragment.TAG
                                )
                            }
                        } else if (view.id == R.id.iv_cart) {
                            showBottomSheet((model as HomeMenuModel))
                        }
                    } // 메뉴 아이템 클릭
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
        it.rvRecent.adapter = recentAdapter
        it.rvRecent.setGridLayoutManager(requireContext())
        it.rvRecent.addItemDecoration(GridItemDecoration(requireContext(),true,noneApplyIndex =0))
    }

    private fun initObserver(){
        lifecycleScope.launch{
            viewModel.recentMenus.collectLatest {
                recentAdapter.submitList(it)
            }
        }
    }

    companion object {
        const val TAG = ".RecentFragment"
        fun newInstance() = RecentFragment()
    }

    override fun showBottomSheet(menu: HomeMenuModel) {
        OrderCartBottomSheetManager.build(childFragmentManager)
            .setOnClickMoveToCartListener {
                fragmentNavigation.popStack() // 최근 본 상품은 장바구니 화면에서만 이동 가능 -> 뒤로 가면 장바구니 -> popStack()
                // 기존의 CartFragment는 지우고 새로운 CartFragment.newInstance를 띄우고 싶음.. 어떻게?
                //fragmentNavigation.replaceFragment(CartFragment.newInstance(), CartFragment.TAG)
            }
            .show(currentMenuModel = menu)
    }
}
