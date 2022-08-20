package com.seom.banchan.ui.home

import com.seom.banchan.ui.model.home.HomeMenuModel

interface CartBottomSheetManager {
    fun showBottomSheet(menu: HomeMenuModel)
}