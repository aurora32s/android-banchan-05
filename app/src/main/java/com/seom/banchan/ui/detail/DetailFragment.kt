package com.seom.banchan.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentDetailBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.detail.DetailMenuModel
import com.seom.banchan.ui.model.detail.MenuDetailModel
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.util.ext.addIconImageView
import com.seom.banchan.util.ext.fromDpToPx
import com.seom.banchan.util.ext.setIconDrawable
import kotlin.math.abs
import kotlin.math.ceil

class DetailFragment(
    private val menuId: String
) : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

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

        // mock data
        val menuDetailInfo = MenuDetailModel(
            id = "HBDEF",
            images = listOf(
                "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_T.jpg",
                "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_S.jpg"
            ),
            name = "오리 주물럭_반조리",
            point = 126,
            deliveryInfo = "서울 경기 새벽 배송, 전국 택배 배송",
            deliveryFee = "2,500원 (40,000원 이상 구매 시 무료)",
            normalPrice = 15800,
            salePrice = 12640,
            detailImages = listOf(
                "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_D1.jpg",
                "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_D2.jpg",
                "http://public.codesquad.kr/jk/storeapp/data/pakage_regular.jpg"
            )
        )
        val detailMenu = DetailMenuModel(
            detailMenu = menuDetailInfo,
            discountRate = ceil((1 - (menuDetailInfo.salePrice / menuDetailInfo.normalPrice.toDouble())) * 100).toInt()
        )
        binding?.detail = detailMenu
        initRecyclerView(detailMenu)
        initAppbar()
    }

    private fun initRecyclerView(detailMenuModel: DetailMenuModel) = binding?.let {
        val detailItem = listOf(
            detailMenuModel,
//            detailMenuModel.detailMenu
        ) + detailMenuModel.detailMenu.detailImages.map {
            ImageSliderModel(imageUrl = it, type = CellType.IMAGE_LIST_CELL)
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
        fun newInstance(menuId: String) = DetailFragment(menuId)
    }
}