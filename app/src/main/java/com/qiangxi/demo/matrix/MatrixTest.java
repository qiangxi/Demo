package com.qiangxi.demo.matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Create By renqiangqiang . 2019/2/15
 */
public class MatrixTest extends View {

    private Matrix mMatrix = new Matrix();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MatrixTest(Context context) {
        this(context, null);
    }

    public MatrixTest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(36);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.concat(mMatrix);
        mMatrix.setRotate(90);
        // canvas.translate(30,20);
        // canvas.rotate(30);
        canvas.drawText("测试", 0, getWidth() / 2F, mPaint);

    }

}
