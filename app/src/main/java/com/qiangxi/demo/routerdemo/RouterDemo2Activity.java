package com.qiangxi.demo.routerdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.qiangxi.demo.R;

public class RouterDemo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router_demo2);
    }

    public void startNext(View view) {
        // ARouter.getInstance().build("")
        //         .withString("name", "zhangsan")
        //         .withInt("age", 31)
        //         .navigation(this, 555, new NavCallback() {
        //             @Override
        //             public void onArrival(Postcard postcard) {
        //                 Bundle extras = postcard.getExtras();
        //                 String height = extras.getString("height");
        //                 Log.e("RouterDemo2Activity", "height=" + height);
        //             }
        //         });
    }
}
