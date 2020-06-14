package com.robert.banyai.wup.presentation.base

import android.view.Gravity

data class ToolbarModel(
    val gravity: Int = Gravity.CENTER,
    val title: String = "",
    val backButtonVisibility: Boolean = false
)