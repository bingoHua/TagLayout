package com.example.taglayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.util.*

class RandTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val textSizeArray = arrayListOf<Int>(16, 25, 30)
    private val testArray = arrayListOf("深圳", "重庆")
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val random = Random()
    private val X_PADDING = 16.dp().toInt()
    private val Y_PADDING = 8.dp().toInt()
    private val CORNER_RADIUS = 4.dp()

    init {
        setTextColor(Color.WHITE)
        textSize = textSizeArray[random.nextInt(3)].toFloat()

        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), CORNER_RADIUS, CORNER_RADIUS, paint)
        super.onDraw(canvas)
    }
}