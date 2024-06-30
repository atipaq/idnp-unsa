package com.example.hangmangame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var partToDraw = 0
    private val paint = Paint()
    private var centerX = 0f
    private var centerY = 0f
    private var scaleFactor = 1f

    init {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        scaleFactor = (w.coerceAtMost(h) / 600f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (partToDraw > 0) drawHead(canvas)
        if (partToDraw > 1) drawTorso(canvas)
        if (partToDraw > 2) drawLeftArm(canvas)
        if (partToDraw > 3) drawRightArm(canvas)
        if (partToDraw > 4) drawLeftLeg(canvas)
        if (partToDraw > 5) drawRightLeg(canvas)
    }

    private fun drawHead(canvas: Canvas) {
        paint.color = Color.rgb(255, 165, 0)
        paint.style = Paint.Style.FILL
        canvas.drawCircle(centerX, centerY - 150 * scaleFactor, 50 * scaleFactor, paint)

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(centerX, centerY - 150 * scaleFactor, 50 * scaleFactor, paint)
    }

    private fun drawTorso(canvas: Canvas) {
        paint.color = Color.rgb(255, 165, 0)
        paint.style = Paint.Style.FILL
        canvas.drawOval(
            centerX - 30 * scaleFactor, centerY - 100 * scaleFactor,
            centerX + 30 * scaleFactor, centerY + 100 * scaleFactor, paint
        )

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawOval(
            centerX - 30 * scaleFactor, centerY - 100 * scaleFactor,
            centerX + 30 * scaleFactor, centerY + 100 * scaleFactor, paint
        )
    }

    private fun drawLeftArm(canvas: Canvas) {
        paint.color = Color.rgb(255, 165, 0)
        paint.style = Paint.Style.FILL
        canvas.drawOval(
            centerX - 100 * scaleFactor, centerY - 50 * scaleFactor,
            centerX - 30 * scaleFactor, centerY, paint
        )

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawOval(
            centerX - 100 * scaleFactor, centerY - 50 * scaleFactor,
            centerX - 30 * scaleFactor, centerY, paint
        )
    }

    private fun drawRightArm(canvas: Canvas) {
        paint.color = Color.rgb(255, 165, 0)
        paint.style = Paint.Style.FILL
        canvas.drawOval(
            centerX + 30 * scaleFactor, centerY - 50 * scaleFactor,
            centerX + 100 * scaleFactor, centerY, paint
        )

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawOval(
            centerX + 30 * scaleFactor, centerY - 50 * scaleFactor,
            centerX + 100 * scaleFactor, centerY, paint
        )
    }

    private fun drawLeftLeg(canvas: Canvas) {
        paint.color = Color.rgb(255, 165, 0)
        paint.style = Paint.Style.FILL
        canvas.drawOval(
            centerX - 50 * scaleFactor,
            centerY + 70 * scaleFactor,
            centerX - 10 * scaleFactor,
            centerY + 210 * scaleFactor,
            paint
        )

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawOval(
            centerX - 50 * scaleFactor,
            centerY + 70 * scaleFactor,
            centerX - 10 * scaleFactor,
            centerY + 210 * scaleFactor,
            paint
        )
    }

    private fun drawRightLeg(canvas: Canvas) {
        paint.color = Color.rgb(255, 165, 0)
        paint.style = Paint.Style.FILL
        canvas.drawOval(
            centerX + 50 * scaleFactor,
            centerY + 70 * scaleFactor,
            centerX + 10 * scaleFactor,
            centerY + 210 * scaleFactor,
            paint
        )

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawOval(
            centerX + 50 * scaleFactor,
            centerY + 70 * scaleFactor,
            centerX + 10 * scaleFactor,
            centerY + 210 * scaleFactor,
            paint
        )
    }

    fun nextPart() {
        if (partToDraw < 6) {
            partToDraw++
            invalidate()
        }
    }

    fun reset() {
        partToDraw = 0
        invalidate()
    }
}
