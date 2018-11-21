package com.qiangxi.demo.routerdemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.qiangxi.demo.R;

// @Route(path = "/activity/routeDemo1")
public class RouterDemo1Activity extends AppCompatActivity {

    // @Autowired
    String name;

    // @Autowired
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router_demo1);
        // ARouter.getInstance().inject(this);

        // Log.e("RouterDemo1Activity", "name=" + name + ",age=" + age);

        // String localname = getIntent().getStringExtra("name");
        // int localage = getIntent().getIntExtra("age", -1);
        // Log.e("RouterDemo1Activity", "localname=" + localname + ",localage=" + localage);
    }

    public void startNext(View view) {
        Intent i = new Intent();
        i.putExtra("height", 145);
        setResult(666, i);
        finish();
    }
}
