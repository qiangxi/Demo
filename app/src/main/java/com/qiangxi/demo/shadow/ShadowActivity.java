package com.qiangxi.demo.shadow;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.shadow.ShadowDrawableWrapper;
import com.qiangxi.demo.R;

import androidx.appcompat.app.AppCompatActivity;

public class ShadowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);

        // test1 format
        TextView text = findViewById(R.id.text);
        text.setText(String.format("正在%s", ""));
        text.setVisibility(View.GONE);

        final ImageView shadowIv = findViewById(R.id.shadowIv);
        // test1 AnimationDrawable
        // shadowIv.setImageResource(R.drawable.common_mic_speak_anim);
        // final AnimationDrawable drawable = (AnimationDrawable) shadowIv.getDrawable();
        // drawable.start();
        shadowIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = getResources().getDrawable(R.drawable.test1);
                ShadowDrawableWrapper wrapper = new ShadowDrawableWrapper(ShadowActivity.this, drawable, 5, 20, 40);
                shadowIv.setImageDrawable(wrapper);
            }
        });

    }
}
