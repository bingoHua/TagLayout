package com.example.taglayout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {

    private val childrenBounds = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineWidthUsed = 0
        var lineMaxHeight = 0
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (lineWidthUsed + child.measuredWidth > specWidth) {
                lineWidthUsed = 0
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            }
            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBounds = childrenBounds[index]
            childBounds.set(lineWidthUsed, heightUsed, lineWidthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            lineWidthUsed += child.measuredWidth
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
            widthUsed = max(lineWidthUsed, widthUsed)
        }
        heightUsed += lineMaxHeight
        val selfWidth = widthUsed
        val selfHeight = heightUsed
        setMeasuredDimension(selfWidth, selfHeight)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val childBound = childrenBounds[index]
            child.layout(childBound.left, childBound.top, childBound.right, childBound.bottom)
        }
    }
}