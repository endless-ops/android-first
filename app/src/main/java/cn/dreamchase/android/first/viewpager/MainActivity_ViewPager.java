package cn.dreamchase.android.first.viewpager;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import cn.dreamchase.android.first.R;

/**
 * -ViewPager 翻页视图
 * 需要PagerAdapter来完成页面和数据的绑定
 * ，这个PagerAdapter是一个基类适配器，经常用来实现app引导图，它的子类有FragmentPagerAdapter和FragmentStatePagerAdapter
 * 和Fragment一起使用
 */
public class MainActivity_ViewPager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpage);

        viewPager.setOffscreenPageLimit(2);  // 设置缓存页数

        viewPager.setCurrentItem(0); // 设置当前显示的item   0 表示第一个

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        fragmentAdapter.addFrament(new FragmentTest("页面1", android.R.color.holo_red_dark));
        fragmentAdapter.addFrament(new FragmentTest("页面2", android.R.color.holo_green_dark));
        fragmentAdapter.addFrament(new FragmentTest("页面3", android.R.color.holo_red_dark));
        fragmentAdapter.addFrament(new FragmentTest("页面4", android.R.color.holo_green_dark));

        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("MainActivity","选中了页面" + (position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}