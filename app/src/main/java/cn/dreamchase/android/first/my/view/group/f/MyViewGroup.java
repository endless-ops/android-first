package cn.dreamchase.android.first.my.view.group.f;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount(); // 获取子View的数量

        int left = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            int childWidth = child.getMeasuredWidth();// 获取子View的宽度
            int childHeight = child.getMeasuredHeight(); // 获取子View的高度

            // 四个参数：left top right bottom
            child.layout(left, 0, left + childWidth, childHeight);
            left += childWidth;
        }
    }

    /**
     * -测量子View的宽高，根据子View的宽高与自己的测量模式来决定自己的宽高
     * -此方法会被调用两次，之后在调用onLayout方法
     *
     * @param widthMeasureSpec  宽
     * @param heightMeasureSpec 高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取ViewGroup的宽高
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec); // 测量所有子View的宽高

        Log.i("package", "测量宽度：" + sizeWidth + "测量高度:" + sizeHeight);

        setMeasuredDimension(sizeWidth, sizeHeight); // 传入测量后的宽高
    }
}
