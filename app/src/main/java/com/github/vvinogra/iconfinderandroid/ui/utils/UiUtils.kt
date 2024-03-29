package com.github.vvinogra.iconfinderandroid.ui.utils

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleIf(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
