package com.seom.banchan.util.ext

import android.graphics.Paint
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.seom.banchan.domain.model.home.MenuModel
import com.seom.banchan.ui.model.CellType
import com.seom.banchan.ui.model.Model
import com.seom.banchan.ui.model.home.HomeMenuModel

@BindingAdapter("text")
fun TextView.setText(data: String?) {
    if (data.isNullOrBlank())
        isGone = true
    else {
        isVisible = true
        text = data
    }
}

@BindingAdapter("visibilityByData")
fun TextView.setVisibilityByData(data: Int?) {
    if (data == null || data == 0) {
        isGone = true
    } else {
        isVisible = true
    }
}

@BindingAdapter("visibilityByModelData")
fun TextView.setVisibilityByModelData(data: HomeMenuModel?) {
    if(data == null ) isGone = true
    else if(data.type == CellType.MENU_RECENT_CELL){
        isGone = true
    } else if(data.discountRate == 0 || data.menu.normalPrice == 0){
        isGone = true
    } else {
        isVisible = true
    }
}

@BindingAdapter("visibilityByModelDataAtRecent")
fun TextView.setVisibilityByModelDataAtRecent(data: HomeMenuModel?) {
    if(data == null ) isGone = true
    else if(data.type == CellType.MENU_CELL){
        isGone = true
    }
    else if( data.menu.normalPrice == 0){
        isGone = true
    } else {
        isVisible = true
    }
}

@BindingAdapter("strikeThrough")
fun TextView.strikeThrough(use: Boolean) {
    paintFlags = if (use) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else 0
}