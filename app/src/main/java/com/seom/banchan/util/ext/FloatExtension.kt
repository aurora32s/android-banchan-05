package com.seom.banchan.util.ext

import android.content.res.Resources
import android.util.DisplayMetrics

fun Float.fromDpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()