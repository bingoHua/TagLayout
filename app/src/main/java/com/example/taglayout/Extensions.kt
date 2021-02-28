package com.example.taglayout

import android.content.res.Resources
import android.util.TypedValue

fun Int.dp():Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)
}