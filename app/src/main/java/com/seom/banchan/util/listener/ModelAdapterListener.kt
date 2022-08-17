package com.seom.banchan.util.listener

import android.view.View
import com.seom.banchan.ui.model.Model

/**
 * model recycler view 에서 발생할 수 있는 아이템
 * 클릭 리스너
 */
interface ModelAdapterListener {
    fun onClick(view: View, model: Model, position: Int)
}