package com.seom.banchan.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentDetailBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.detail.DetailMenuModel
import com.seom.banchan.ui.model.detail.MenuDetailModel
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.util.ext.addIconImageView
import com.seom.banchan.util.ext.setIconDrawable
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
            discountRate = ceil(menuDetailInfo.salePrice / menuDetailInfo.normalPrice.toDouble() * 100).toInt()
        )
        initViewPager(detailMenu.detailMenu.images)
        bindViewPager()
        initRecyclerView(detailMenu)
    }

    private fun initViewPager(images: List<String>) = binding?.let {
        it.vpMenuImage.offscreenPageLimit = 1

        val imageSliderAdapter = ModelRecyclerAdapter<ImageSliderModel>()
        it.vpMenuImage.adapter = imageSliderAdapter

        imageSliderAdapter.submitList(images.map { image ->
            ImageSliderModel(
                imageUrl = image,
                type = CellType.IMAGE_SLIDER_CELL
            )
        })
        initIndicator(images.size)
    }

    private fun bindViewPager() = binding?.vpMenuImage?.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setCurrentIndicator(position)
        }
    })

    private fun initIndicator(count: Int) = binding?.let {
        (0 until count).forEach { index ->
            it.llIndicator.addIconImageView(R.drawable.ic_normal_indicator, 10)
        }

        setCurrentIndicator(0)
    }

    private fun setCurrentIndicator(position: Int) = binding?.let {
        for (index in 0 until it.llIndicator.childCount) {
            (it.llIndicator.getChildAt(index) as ImageView).setIconDrawable(
                if (index == position) R.drawable.ic_active_indicator
                else R.drawable.ic_normal_indicator
            )
        }
    }

    private fun initRecyclerView(detailMenuModel: DetailMenuModel) = binding?.let {
        val detailItem = listOf(detailMenuModel) + detailMenuModel.detailMenu.detailImages.map {
            ImageSliderModel(imageUrl = it, type = CellType.IMAGE_LIST_CELL)
        }

        it.rvMenuInfo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val menuDetailAdapter = ModelRecyclerAdapter<Model>()
        it.rvMenuInfo.adapter = menuDetailAdapter
        menuDetailAdapter.submitList(detailItem)
    }

    companion object {
        const val TAG = ".DetailFragment"
        fun newInstance(menuId: String) = DetailFragment(menuId)
    }
}