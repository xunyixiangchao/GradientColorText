package com.lis.gradientcolortext;

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lis.gradientcolortext.fragment.ViewpagerFragment;
import com.lis.gradientcolortext.view.GradientColorText;

import java.util.ArrayList;
import java.util.List;


public class ViewpagerActivity extends AppCompatActivity {
    LinearLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.setData(getData());
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(adapter);
        for (int i = 0; i < getData().size(); i++) {
            GradientColorText text = new GradientColorText(this);
            text.setDefaultColor(R.color.black);
            text.setSelectedColor(R.color.red);
            text.setInputText("第" + (i + 1) + "页");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            tabLayout.addView(text, layoutParams);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset >= 0) {
                    GradientColorText left = (GradientColorText) tabLayout.getChildAt(position);
                    GradientColorText right = (GradientColorText) tabLayout.getChildAt(position + 1);
                    left.setScrollMode(GradientColorText.MODE_RIGHT);
                    left.setPercent(1 - positionOffset);
                    if (right != null) {
                        right.setScrollMode(GradientColorText.MODE_LEFT);
                        right.setPercent(positionOffset);
                    }

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);

    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        data.add(new ViewpagerFragment("第一页"));
        data.add(new ViewpagerFragment("第二页"));
        data.add(new ViewpagerFragment("第三页"));
        data.add(new ViewpagerFragment("第四页"));
        return data;
    }

    class ViewpagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mData;

        public void setData(List<Fragment> data) {
            mData = data;
        }

        public ViewpagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

    }
}
