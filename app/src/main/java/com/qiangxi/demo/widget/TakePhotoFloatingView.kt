package com.qiangxi.demo.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Create By renqiangqiang(qiang_xi) on 2018/12/27
 * 预览浮层
 */
class TakePhotoFloatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val LEFT_MARGIN = 100
        private const val TOP_MARGIN = 100
        private val BG_COLOR = Color.parseColor("#CC000000")
        private val mDstInMode: PorterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    }

    private val mOvalPath = Path()

    private val mRectPath = Path()
    private val mOvalRectF = RectF()
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mBitmap: Bitmap? = null


    init {
        mPaint.style = Paint.Style.FILL
        mPaint.color = BG_COLOR
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            mRectPath.reset()
            mRectPath.addRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), Path.Direction.CCW)
            mOvalRectF.left = (left + LEFT_MARGIN).toFloat()
            mOvalRectF.top = (top + TOP_MARGIN).toFloat()
            mOvalRectF.right = (right - LEFT_MARGIN).toFloat()
            mOvalRectF.bottom = (bottom - TOP_MARGIN).toFloat()
            mOvalPath.reset()
            mOvalPath.addOval(mOvalRectF, Path.Direction.CCW)
            draw()
        }
    }

    private fun draw() {
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(mBitmap!!)
        c.save()
        c.drawPath(mRectPath, mPaint)
        mPaint.xfermode = mDstInMode
        c.drawPath(mOvalPath, mPaint)
        mPaint.xfermode = null
        c.restore()
    }

    override fun onDraw(canvas: Canvas?) {
        mBitmap?.let { canvas?.drawBitmap(it, 0F, 0F, mPaint) }
    }
}