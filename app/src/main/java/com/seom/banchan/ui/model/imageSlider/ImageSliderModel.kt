package com.seom.banchan.ui.model.imageSlider

import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model

data class ImageSliderModel(
    override val id: String = "image",
    override val type: CellType = CellType.IMAGE_SLIDER,
    val imageUrl: String
) : Model(id, type)
