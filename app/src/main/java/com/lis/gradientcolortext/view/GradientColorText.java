package com.lis.gradientcolortext.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.IntDef;
import androidx.appcompat.widget.AppCompatTextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lis on 2020/7/15.
 */
public class GradientColorText extends AppCompatTextView {
    private int defaultColor;
    private int selectedColor;
    private Paint mPaint;
    private float mPercent;
    private Context mContext;
    private String inputText;
    @SCROLL_MODE
    private int mScrollMode = MODE_LEFT;

    public void setScrollMode(@SCROLL_MODE int mode) {
        mScrollMode = mode;
    }

    @IntDef(flag = true, value = {MODE_LEFT, MODE_RIGHT, MODE_TOP, MODE_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SCROLL_MODE {

    }

    public static final int MODE_LEFT = 0;
    public static final int MODE_RIGHT = 1;
    public static final int MODE_TOP = 2;
    public static final int MODE_BOTTOM = 3;

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }


    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float percent) {
        mPercent = percent;
        invalidate();
    }


    public String getInputText() {
        return inputText;
    }

    public void setInputText(String text) {
        inputText = text;
    }


    public GradientColorText(Context context) {
        this(context, null);
    }

    public GradientColorText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientColorText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    Typeface tf;

    private void init() {
        mPaint = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mScrollMode) {
            case MODE_LEFT:
                drawLeftDefaultText(canvas);
                drawLeftSelectedText(canvas);
                break;
            case MODE_RIGHT:
                drawRightDefaultText(canvas);
                drawRightSelectedText(canvas);
                break;
        }


    }
    private void drawRightDefaultText(Canvas canvas) {
        canvas.save();
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setTypeface(tf);
        mPaint.setColor(mContext.getResources().getColor(defaultColor));
        //1.设置文字垂直居中
        // mPaint.setTextAlign(Paint.Align.CENTER);
        //2. 获得文字宽   float width = mPaint.measureText(mText);
        float width = mPaint.measureText(inputText);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        float left = getWidth() / 2 - width / 2;
        //避免重复绘制
        float left_x = left + width * mPercent;
        Rect rect = new Rect(getWidth(), 0, (int) left_x, getHeight());
        canvas.clipRect(rect);

        float y = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.ascent) / 2 - fontMetrics.bottom;
        canvas.drawText(inputText, left, y, mPaint);
        canvas.restore();
    }

    private void drawRightSelectedText(Canvas canvas) {
        canvas.save();
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setTypeface(tf);
        mPaint.setColor(mContext.getResources().getColor(selectedColor));
        //1.设置文字垂直居中
        //mPaint.setTextAlign(Paint.Align.CENTER);
        //2. 获得文字宽   float width = mPaint.measureText(mText);
        float width = mPaint.measureText(inputText);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        //避免重复绘制
        float left = getWidth() / 2 - width / 2;
        Rect rect = new Rect((int) (left + getWidth() * mPercent), 0, (int) left, getHeight());
        canvas.clipRect(rect);

        float y = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.ascent) / 2 - fontMetrics.bottom;
        canvas.drawText(inputText, left, y, mPaint);
        canvas.restore();
    }



    private void drawLeftDefaultText(Canvas canvas) {
        canvas.save();
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setTypeface(tf);
        mPaint.setColor(mContext.getResources().getColor(defaultColor));
        //1.设置文字垂直居中
        // mPaint.setTextAlign(Paint.Align.CENTER);
        //2. 获得文字宽   float width = mPaint.measureText(mText);
        float width = mPaint.measureText(inputText);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        float left = getWidth() / 2 - width / 2;
        //避免重复绘制
        float left_x = left + width * mPercent;
        Rect rect = new Rect((int) left_x, 0, getWidth(), getHeight());
        canvas.clipRect(rect);

        float y = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.ascent) / 2 - fontMetrics.bottom;
        canvas.drawText(inputText, left, y, mPaint);
        canvas.restore();
    }

    private void drawLeftSelectedText(Canvas canvas) {
        canvas.save();
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setTypeface(tf);
        mPaint.setColor(mContext.getResources().getColor(selectedColor));
        //1.设置文字垂直居中
        //mPaint.setTextAlign(Paint.Align.CENTER);
        //2. 获得文字宽   float width = mPaint.measureText(mText);
        float width = mPaint.measureText(inputText);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        //避免重复绘制
        float left = getWidth() / 2 - width / 2;
        Rect rect = new Rect((int) left, 0, (int) (left + getWidth() * mPercent), getHeight());
        canvas.clipRect(rect);

        float y = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.ascent) / 2 - fontMetrics.bottom;
        canvas.drawText(inputText, left, y, mPaint);
        canvas.restore();
    }


    public void setCustomTypeface(Typeface tf) {
        this.tf = tf;
    }
}
