package com.seom.banchan.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentRecentBinding
import com.seom.banchan.ui.adapter.DiffItemCallback
import com.seom.banchan.ui.adapter.ItemDecoration.GridItemDecoration
import com.seom.banchan.ui.adapter.PagingAdapter
import com.seom.banchan.ui.base.BaseFragment
import com.seom.banchan.ui.detail.DetailFragment
import com.seom.banchan.ui.home.CartBottomSheetManager
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HomeMenuModel
import com.seom.banchan.ui.view.OrderCartBottomSheetManager
import com.seom.banchan.util.listener.ModelAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentFragment : BaseFragment(), CartBottomSheetManager {
    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding

    private val viewModel: RecentViewModel by viewModels()

    private val recentAdapter: PagingAdapter<Model> by lazy {
        PagingAdapter(
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
            },
            diffUtilItemCallback = DiffItemCallback<Model>(
                onAreItemsTheSame = { oldItem, newItem ->
                    (oldItem as HomeMenuModel).menu == (newItem as HomeMenuModel).menu
                },
                onAreContentsTheSame = { oldItem, newItem ->
                    oldItem == newItem
                }
            )
        )
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
    }

    private fun initRecyclerView() = binding?.let {
        it.rvRecent.adapter = recentAdapter
        it.rvRecent.layoutManager = GridLayoutManager(context, 2)
        it.rvRecent.addItemDecoration(
            GridItemDecoration(
                requireContext(),
                true,
                noneApplyIndex = 0
            )
        )
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.recentMenus.collectLatest {
                recentAdapter.submitData(it.map {
                    it as Model
                })
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
                fragmentNavigation.popStack()
            }
            .show(currentMenuModel = menu)
    }
}
