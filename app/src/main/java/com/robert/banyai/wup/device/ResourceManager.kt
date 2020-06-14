package com.robert.banyai.wup.device

import android.content.Context
import androidx.annotation.DrawableRes
import javax.inject.Inject

class ResourceManager @Inject constructor(context: Context) {

    private val packageName by lazy { context.packageName }
    private val resources by lazy { context.resources }

    @DrawableRes
    fun findDrawableResourceId(resourceName: String): Int {
        return resources.getIdentifier(resourceName, "drawable", packageName)
    }
}