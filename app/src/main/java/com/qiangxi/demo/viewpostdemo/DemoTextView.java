package com.qiangxi.demo.viewpostdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Create By renqiangqiang(qiang_xi) on 2018/9/20
 */
public class DemoTextView extends TextView {
    private int count;
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            --count;
            postDelayed(this, 1000);
            setText(count + "");
        }
    };


    public DemoTextView(Context context) {
        super(context);
    }

    public DemoTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void start() {
        removeCallbacks(task);
        setText(count + "");
        postDelayed(task, 1000);
    }
}
