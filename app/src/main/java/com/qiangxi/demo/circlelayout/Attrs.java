package com.qiangxi.demo.circlelayout;

/**
 * Create By renqiangqiang(qiang_xi) on 2018/11/19
 */

public interface Attrs {
    void setRadius(int radius);

    boolean isClipBackground();

    void setClipBackground(boolean clipBackground);

    boolean isRoundAsCircle();

    void setRoundAsCircle(boolean roundAsCircle);

    float getTopLeftRadius();

    void setTopLeftRadius(int topLeftRadius);

    float getTopRightRadius();

    void setTopRightRadius(int topRightRadius);

    float getBottomLeftRadius();

    void setBottomLeftRadius(int bottomLeftRadius);

    float getBottomRightRadius();

    void setBottomRightRadius(int bottomRightRadius);

    int getStrokeWidth();

    void setStrokeWidth(int strokeWidth);

    int getStrokeColor();

    void setStrokeColor(int strokeColor);
}

