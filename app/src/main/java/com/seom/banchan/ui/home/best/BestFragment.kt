package com.seom.banchan.ui.home.best

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentBestBinding
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HomeMenuModel

class BestFragment : Fragment() {
    private var _binding: FragmentBestBinding? = null
    private val binding get() = _binding

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
        initHeader()
        initRecyclerView()
    }

    private fun initHeader() = binding?.let {
        it.tvHeader.text = getString(R.string.header_best)
        it.tvChip.text = getString(R.string.tab_best)
    }

    private fun initRecyclerView() = binding?.let {
        it.rvBest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val homeAdapter = ModelRecyclerAdapter<CategoryMenuModel>()
        it.rvBest.adapter = homeAdapter

        homeAdapter.submitList(
            (1..10).map { categoryId ->
                CategoryMenuModel(
                    id = categoryId.toLong(),
                    type = CellType.MENU_LIST_CELL,
                    categoryName = "category $categoryId",
                    menus = (1..10).map { menuId ->
                        HomeMenuModel(
                            id = menuId.toLong(),
                            menu = MenuModel(
                                id = "H$menuId",
                                name = "menu $menuId",
                                deliveryType = emptyList(),
                                image = "",
                                description = "",
                                salePrice = 1000,
                                normalPrice = 1000
                            ),
                            discountRate = menuId
                        )
                    }
                )
            }
        )
    }

    companion object {
        const val TAG = ".BestFragment"
        fun newInstance() = BestFragment()
    }

}