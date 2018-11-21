package com.qiangxi.demo.circlelayout;

/**
 * Create By renqiangqiang(qiang_xi) on 2018/11/19
 */

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Checkable;

import com.qiangxi.demo.R;

import java.util.ArrayList;

/**
 * 作用：圆角辅助工具,for compat Android P
 */
public class HelperCompatV28 {
    public float[] radii = new float[8];   // top-left, top-right, bottom-right, bottom-left
    public Path mClipPath;                 // 剪裁区域路径
    public Paint mPaint;                   // 画笔
    public boolean mRoundAsCircle = false; // 圆形
    public int mDefaultStrokeColor;        // 默认描边颜色
    public int mStrokeColor;               // 描边颜色
    public ColorStateList mStrokeColorStateList;// 描边颜色的状态
    public int mStrokeWidth;               // 描边半径
    public boolean mClipBackground;        // 是否剪裁背景
    public Region mAreaRegion;             // 内容区域
    public int mEdgeFix = 10;              // 边缘修复
    public Rect mLayer;                   // 画布图层大小
    public boolean mChecked;              // 是否是 check 状态
    public OnCheckedChangeListener mOnCheckedChangeListener;
    RectF areas = new RectF();
    private RectF roundRect = new RectF();

    private int roundCorner;

    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Attrs);
        mRoundAsCircle = ta.getBoolean(R.styleable.Attrs_round_as_circle, false);
        mStrokeColorStateList = ta.getColorStateList(R.styleable.Attrs_stroke_color);
        if (null != mStrokeColorStateList) {
            mStrokeColor = mStrokeColorStateList.getDefaultColor();
            mDefaultStrokeColor = mStrokeColorStateList.getDefaultColor();
        } else {
            mStrokeColor = Color.WHITE;
            mDefaultStrokeColor = Color.WHITE;
        }
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.Attrs_stroke_width, 0);
        mClipBackground = ta.getBoolean(R.styleable.Attrs_clip_background, false);
        roundCorner = ta.getDimensionPixelSize(R.styleable.Attrs_round_corner, 0);
        int roundCornerTopLeft = ta.getDimensionPixelSize(
                R.styleable.Attrs_round_corner_top_left, roundCorner);
        int roundCornerTopRight = ta.getDimensionPixelSize(
                R.styleable.Attrs_round_corner_top_right, roundCorner);
        int roundCornerBottomLeft = ta.getDimensionPixelSize(
                R.styleable.Attrs_round_corner_bottom_left, roundCorner);
        int roundCornerBottomRight = ta.getDimensionPixelSize(
                R.styleable.Attrs_round_corner_bottom_right, roundCorner);
        ta.recycle();

        radii[0] = roundCornerTopLeft;
        radii[1] = roundCornerTopLeft;

        radii[2] = roundCornerTopRight;
        radii[3] = roundCornerTopRight;

        radii[4] = roundCornerBottomRight;
        radii[5] = roundCornerBottomRight;

        radii[6] = roundCornerBottomLeft;
        radii[7] = roundCornerBottomLeft;

        mLayer = new Rect();
        mClipPath = new Path();
        mAreaRegion = new Region();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onSizeChanged(View view, int w, int h) {
        mLayer.set(0, 0, w, h);
        // refreshRegion(view);
        view.setClipToOutline(true);
        if (mRoundAsCircle) {
            view.setOutlineProvider(new CircleOutlineProvider());
        } else {
            view.setOutlineProvider(new RoundOutlineProvider());
        }
    }


    //--- Selector 支持 ----------------------------------------------------------------------------

    public void refreshRegion(View view) {
        final float w = mLayer.width();
        final float h = mLayer.height();

        areas.left = view.getPaddingLeft();
        areas.top = view.getPaddingTop();
        areas.right = w - view.getPaddingRight();
        areas.bottom = h - view.getPaddingBottom();
        mClipPath.reset();
        if (mRoundAsCircle) {
            final float d = Math.min(areas.width(), areas.height());
            final float r = d / 2;
            PointF center = new PointF(w / 2, h / 2);
            mClipPath.addCircle(center.x, center.y, r, Path.Direction.CW);
        } else {
            mClipPath.addRoundRect(areas, radii, Path.Direction.CW);
        }
        mClipPath.moveTo(-mEdgeFix, -mEdgeFix);  // 通过空操作让Path区域占满画布
        mClipPath.moveTo(w + mEdgeFix, h + mEdgeFix);
        Region clip = new Region((int) areas.left, (int) areas.top,
                (int) areas.right, (int) areas.bottom);
        mAreaRegion.setPath(mClipPath, clip);
    }

    public void onClipDraw(Canvas canvas) {

        if (mStrokeWidth > 0) {
            // 支持半透明描边，将与描边区域重叠的内容裁剪掉
            mPaint.setColor(Color.WHITE);
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setStyle(Paint.Style.STROKE);
            final int d = Math.min(mLayer.width(), mLayer.height());
            final int r = (d - mStrokeWidth) / 2;
            if (mRoundAsCircle) {
                canvas.drawCircle(mLayer.centerX(), mLayer.centerY(), r, mPaint);
            } else {
                roundRect.left = mLayer.left + mStrokeWidth / 2;
                roundRect.top = mLayer.top + mStrokeWidth / 2;
                roundRect.right = mLayer.right - mStrokeWidth / 2;
                roundRect.bottom = mLayer.bottom - mStrokeWidth / 2;
                canvas.drawRoundRect(roundRect, (roundCorner + mStrokeWidth) / 2, (roundCorner + mStrokeWidth) / 2, mPaint);
            }
        }
    }

    public void drawableStateChanged(View view) {
        if (view instanceof Attrs) {
            ArrayList<Integer> stateListArray = new ArrayList<>();
            if (view instanceof Checkable) {
                stateListArray.add(android.R.attr.state_checkable);
                if (((Checkable) view).isChecked()) {
                    stateListArray.add(android.R.attr.state_checked);
                }
            }
            if (view.isEnabled()) {
                stateListArray.add(android.R.attr.state_enabled);
            }
            if (view.isFocused()) {
                stateListArray.add(android.R.attr.state_focused);
            }
            if (view.isPressed()) {
                stateListArray.add(android.R.attr.state_pressed);
            }
            if (view.isHovered()) {
                stateListArray.add(android.R.attr.state_hovered);
            }
            if (view.isSelected()) {
                stateListArray.add(android.R.attr.state_selected);
            }
            if (view.isActivated()) {
                stateListArray.add(android.R.attr.state_activated);
            }
            if (view.hasWindowFocus()) {
                stateListArray.add(android.R.attr.state_window_focused);
            }

            if (mStrokeColorStateList != null && mStrokeColorStateList.isStateful()) {
                int[] stateList = new int[stateListArray.size()];
                for (int i = 0; i < stateListArray.size(); i++) {
                    stateList[i] = stateListArray.get(i);
                }
                int stateColor = mStrokeColorStateList.getColorForState(stateList, mDefaultStrokeColor);
                ((Attrs) view).setStrokeColor(stateColor);
            }
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(View view, boolean isChecked);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class RoundOutlineProvider extends ViewOutlineProvider {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, mLayer.right, mLayer.bottom, roundCorner);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class CircleOutlineProvider extends ViewOutlineProvider {
        @Override
        public void getOutline(View view, Outline outline) {
            final int radius = (Math.min(mLayer.width(), mLayer.height()) / 2);
            final int centerX = ((mLayer.right - mLayer.left) / 2);
            final int centerY = ((mLayer.bottom - mLayer.top) / 2);
            outline.setOval(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        }
    }
}

