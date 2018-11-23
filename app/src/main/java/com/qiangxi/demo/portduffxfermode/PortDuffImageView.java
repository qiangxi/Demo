package com.qiangxi.demo.portduffxfermode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.qiangxi.demo.R;

import androidx.annotation.Nullable;

/**
 * Create By renqiangqiang(qiang_xi) on 2018/11/21
 */
@SuppressLint("AppCompatCustomView")
public class PortDuffImageView extends ImageView {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF mRect = new RectF();

    private Path mPath = new Path();

    private int mRadius = 30;

    public PortDuffImageView(Context context) {
        this(context, null);
    }

    public PortDuffImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PortDuffImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = w;
        mRect.bottom = h;
        mPath.reset();
        mPath.addRoundRect(mRect, mRadius, mRadius, Path.Direction.CCW);
    }

    // @Override
    // protected void onDraw(Canvas canvas) {
    //     //9.0以下裁剪图片为圆角矩形，实现方式为:Path+PorterDuffXfermode
    //     int count = canvas.saveLayer(mRect, mPaint, Canvas.ALL_SAVE_FLAG);
    //     super.onDraw(canvas);
    //     mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    //     mPaint.setColor(Color.GREEN);
    //     mPaint.setStyle(Paint.Style.FILL);
    //     canvas.drawPath(mPath, mPaint);
    //     canvas.restoreToCount(count);
    // }

    // @Override
    // protected void onDraw(Canvas canvas) {
    //     //9.0以下裁剪图片为圆角矩形，实现方式为BitmapShader
    //     mPaint.setColor(Color.GREEN);
    //     mPaint.setStyle(Paint.Style.FILL);
    //     Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.test);
    //     mPaint.setShader(new BitmapShader(bp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    //     canvas.drawRoundRect(mRect, mRadius, mRadius, mPaint);
    // }
}
