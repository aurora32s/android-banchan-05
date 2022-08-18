package com.seom.banchan.ui.adapter.viewholder.detail

import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.seom.banchan.R
import com.seom.banchan.databinding.ItemMenuInfoBinding
import com.seom.banchan.ui.adapter.ModelRecyclerAdapter
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.detail.DetailMenuUiModel
import com.seom.banchan.ui.model.imageSlider.ImageSliderModel
import com.seom.banchan.util.ext.addIconImageView
import com.seom.banchan.util.ext.setIconDrawable

class MenuInfoViewHolder(
    private val binding: ItemMenuInfoBinding
) : ModelViewHolder<DetailMenuUiModel>(binding) {

    override fun bindData(model: DetailMenuUiModel) {
        binding.detail = model

        initViewPager(model.detailMenu.images)
        bindViewPager()
    }

    private fun initViewPager(images: List<String>?) = with(binding) {
        if (images == null) return
        vpMenuImage.offscreenPageLimit = 1

        val imageSliderAdapter = ModelRecyclerAdapter<ImageSliderModel>()
        vpMenuImage.adapter = imageSliderAdapter

        imageSliderAdapter.submitList(images.map { image ->
            ImageSliderModel(
                imageUrl = image,
                type = CellType.IMAGE_SLIDER_CELL
            )
        })
        initIndicator(images.size)
    }

    private fun bindViewPager() = binding.vpMenuImage.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setCurrentIndicator(position)
        }
    })

    private fun initIndicator(count: Int) = with(binding) {
        llIndicator.removeAllViews()
        (0 until count).forEach { index ->
            llIndicator.addIconImageView(R.drawable.ic_normal_indicator, 10)
        }

        setCurrentIndicator(0)
    }

    private fun setCurrentIndicator(position: Int) = with(binding) {
        for (index in 0 until llIndicator.childCount) {
            (llIndicator.getChildAt(index) as ImageView).setIconDrawable(
                if (index == position) R.drawable.ic_active_indicator
                else R.drawable.ic_normal_indicator
            )
        }
    }
}