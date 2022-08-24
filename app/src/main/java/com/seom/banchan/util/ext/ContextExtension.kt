package com.seom.banchan.util.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.seom.banchan.R

/**
 * toast
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/**
 * snack bar
 */
fun Context.snackBarForReRequest(
    root: View,
    onClick: View.OnClickListener
) {
    Snackbar.make(root, R.string.msg_data_re_request, Snackbar.LENGTH_LONG)
        .setAction(R.string.btn_re_request, onClick)
        .show()
}