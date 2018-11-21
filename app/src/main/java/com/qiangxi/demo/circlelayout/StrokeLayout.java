package com.qiangxi.demo.circlelayout;

/**
 * Create By renqiangqiang(qiang_xi) on 2018/11/19
 */

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/**
 * 作用：圆角相对布局
 */
public class StrokeLayout extends RelativeLayout implements Checkable, Attrs {
    HelperCompatV28 mHelper;

    public StrokeLayout(Context context) {
        this(context, null);
    }

    public StrokeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHelper = new HelperCompatV28();
        mHelper.initAttrs(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHelper.onSizeChanged(this, w, h);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        // canvas.saveLayer(mHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);

        super.dispatchDraw(canvas);
        mHelper.onClipDraw(canvas);

        // canvas.restore();
    }


    @Override
    public void draw(Canvas canvas) {
        mHelper.refreshRegion(this);
        if (mHelper.mClipBackground) {
            canvas.save();
            canvas.clipPath(mHelper.mClipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
            refreshDrawableState();
        } else if (action == MotionEvent.ACTION_CANCEL) {
            setPressed(false);
            refreshDrawableState();
        }
        if (!mHelper.mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
            setPressed(false);
            refreshDrawableState();
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    //--- 公开接口 ----------------------------------------------------------------------------------

    public void setRadius(int radius) {
        for (int i = 0; i < mHelper.radii.length; i++) {
            mHelper.radii[i] = radius;
        }
        invalidate();
    }

    public boolean isClipBackground() {
        return mHelper.mClipBackground;
    }

    public void setClipBackground(boolean clipBackground) {
        mHelper.mClipBackground = clipBackground;
        invalidate();
    }

    public boolean isRoundAsCircle() {
        return mHelper.mRoundAsCircle;
    }

    public void setRoundAsCircle(boolean roundAsCircle) {
        mHelper.mRoundAsCircle = roundAsCircle;
        invalidate();
    }

    public float getTopLeftRadius() {
        return mHelper.radii[0];
    }

    public void setTopLeftRadius(int topLeftRadius) {
        mHelper.radii[0] = topLeftRadius;
        mHelper.radii[1] = topLeftRadius;
        invalidate();
    }

    public float getTopRightRadius() {
        return mHelper.radii[2];
    }

    public void setTopRightRadius(int topRightRadius) {
        mHelper.radii[2] = topRightRadius;
        mHelper.radii[3] = topRightRadius;
        invalidate();
    }

    public float getBottomLeftRadius() {
        return mHelper.radii[4];
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        mHelper.radii[4] = bottomLeftRadius;
        mHelper.radii[5] = bottomLeftRadius;
        invalidate();
    }

    public float getBottomRightRadius() {
        return mHelper.radii[6];
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        mHelper.radii[6] = bottomRightRadius;
        mHelper.radii[7] = bottomRightRadius;
        invalidate();
    }

    public int getStrokeWidth() {
        return mHelper.mStrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        mHelper.mStrokeWidth = strokeWidth;
        invalidate();
    }

    public int getStrokeColor() {
        return mHelper.mStrokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        mHelper.mStrokeColor = strokeColor;
        invalidate();
    }


    //--- Selector 支持 ----------------------------------------------------------------------------

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        mHelper.drawableStateChanged(this);
    }

    @Override
    public boolean isChecked() {
        return mHelper.mChecked;
    }

    @Override
    public void setChecked(boolean checked) {
        if (mHelper.mChecked != checked) {
            mHelper.mChecked = checked;
            refreshDrawableState();
            if (mHelper.mOnCheckedChangeListener != null) {
                mHelper.mOnCheckedChangeListener.onCheckedChanged(this, mHelper.mChecked);
            }
        }
    }

    @Override
    public void toggle() {
        setChecked(!mHelper.mChecked);
    }

    // public void setOnCheckedChangeListener(Helper.OnCheckedChangeListener listener) {
    //     mHelper.mOnCheckedChangeListener = listener;
    // }
}
