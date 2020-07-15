package com.lis.gradientcolortext;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lis.gradientcolortext.view.GradientColorText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewpagerActivity.class);
                startActivity(intent);
            }
        });

        final GradientColorText text = findViewById(R.id.text);
        text.setInputText("Android");
        text.setDefaultColor(R.color.green);
        text.setSelectedColor(R.color.red);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/1.ttf");
        text.setCustomTypeface(tf);

        //属性动画
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator.ofFloat(text, "percent", 0, 1).setDuration(2500).start();
            }
        }, 1000);
    }
}
