package com.qiangxi.demo.viewpostdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qiangxi.demo.R;

public class MainActivity extends AppCompatActivity {

    private int count = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DemoTextView demo = findViewById(R.id.demoTv);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demo.setCount(count);
                demo.start();
            }
        });
    }
}
