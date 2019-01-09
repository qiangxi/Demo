package com.qiangxi.demo.viewpostdemo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.qiangxi.demo.R;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int count = 1000;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DemoTextView demo = findViewById(R.id.demoTv);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // demo.setCount(count);
                // demo.start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        test01("path1");
                    }
                }).start();
                 new Thread(new Runnable() {
                    @Override
                    public void run() {
                        test01("path2");
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        test01("path3");
                    }
                }).start();
            }
        });
    }

    /**
     * 测试连续调用，参数不同，耗时不同的情况下，走到run方法时，参数是否还是正确的
     */
    private void test01(final String path) {
        final long seconds = new Random().nextInt(4000) + 1000;
        Log.e("tag", "path = " + path + ",seconds = " + seconds);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("tag", "run path = " + path + ", seconds = " + seconds);
            }
        }, seconds);

    }
}
