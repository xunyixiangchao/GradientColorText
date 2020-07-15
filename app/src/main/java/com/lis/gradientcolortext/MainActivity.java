package com.lis.gradientcolortext;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.lis.gradientcolortext.view.GradientColorText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GradientColorText text = findViewById(R.id.text);
        text.setInputText("Android");
        text.setDefaultColor(R.color.green);
        text.setSelectedColor(R.color.red);
        Typeface  tf = Typeface.createFromAsset(getAssets(), "fonts/1.ttf");
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
