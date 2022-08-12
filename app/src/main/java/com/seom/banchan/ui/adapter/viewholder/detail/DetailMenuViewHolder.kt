package com.seom.banchan.ui.adapter.viewholder.detail

<<<<<<< HEAD
import com.seom.banchan.databinding.ItemMenuDetailBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.MenuDetailModel

class DetailMenuViewHolder(
    private val binding: ItemMenuDetailBinding
): ModelViewHolder<MenuDetailModel>(binding) {
    override fun bind(model: MenuDetailModel) {
        binding.menu = model
    }
}
=======
import com.seom.banchan.databinding.ItemImageSliderBinding
import com.seom.banchan.databinding.ItemMenuInfoBinding
import com.seom.banchan.ui.adapter.viewholder.ModelViewHolder
import com.seom.banchan.ui.model.detail.DetailMenuModel

class DetailMenuViewHolder(
    private val binding: ItemMenuInfoBinding
) : ModelViewHolder<DetailMenuModel>(binding) {
    override fun bind(model: DetailMenuModel) {
        binding.detail = model
    }
}
>>>>>>> c419be4 ([FEAT] #8 - 상세 화면의 메뉴 정보와 상세 설명 이미지 UI 추가 완료)
