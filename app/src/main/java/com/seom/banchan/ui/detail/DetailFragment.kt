package com.seom.banchan.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentDetailBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.util.ext.addIconImageView
import com.seom.banchan.util.ext.setIconDrawable

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

        initViewPager()
        bindViewPager()
    }

    private fun initViewPager() = binding?.let {
        it.vpMenuImage.offscreenPageLimit = 1

        val imageSliderAdapter = ModelRecyclerAdapter<ImageSliderModel>()
        it.vpMenuImage.adapter = imageSliderAdapter

        val images = listOf(
            ImageSliderModel(imageUrl = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_D1.jpg"),
            ImageSliderModel(imageUrl = "http://public.codesquad.kr/jk/storeapp/data/main/1155_ZIP_P_0081_D2.jpg"),
            ImageSliderModel(imageUrl = "http://public.codesquad.kr/jk/storeapp/data/pakage_regular.jpg")
        )
        imageSliderAdapter.submitList(images)
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

    companion object {
        const val TAG = ".DetailFragment"
        fun newInstance(menuId: String) = DetailFragment(menuId)
    }
}