package com.seom.banchan.ui.home.best

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.seom.banchan.databinding.FragmentBestBinding
import com.seom.banchan.domain.model.MenuModel
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.CategoryMenuModel
import com.seom.banchan.ui.model.home.HeaderMenuModel
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
        initRecyclerView()
    }

    private fun initRecyclerView() = binding?.let {
        it.rvBest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val homeAdapter = ModelRecyclerAdapter<Model>()
        it.rvBest.adapter = homeAdapter

        homeAdapter.submitList(
            mutableListOf<Model>(
                HeaderMenuModel(id = -1, title = "hello world")
            ).apply {
                addAll((1..10).map { categoryId ->
                    CategoryMenuModel(
                        id = categoryId.toLong(),
                        type = CellType.MENU_LIST_CELL,
                        categoryName = "category $categoryId",
                        menus = (0..10).map { menuId ->
                            HomeMenuModel(
                                id = menuId.toLong(),
                                menu = MenuModel(
                                    id = "H$menuId",
                                    name = "menu $menuId",
                                    deliveryType = emptyList(),
                                    image = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
                                    description = "description $menuId",
                                    salePrice = menuId * 1000,
                                    normalPrice = 1000
                                ),
                                discountRate = menuId
                            )
                        }
                    )
                })
            }.toList()
        )
    }

    companion object {
        const val TAG = ".BestFragment"
        fun newInstance() = BestFragment()
    }

}