package cn.dreamchase.android.first.my.view.group.n;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * -
 */
public class MyViewGroupN extends ViewGroup {
    public MyViewGroupN(Context context) {
        super(context);
    }

    public MyViewGroupN(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupN(Context context, AttributeSet attrs, int defStyleAttr) {
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
        // 获取此ViewGroup计算模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 获取ViewGroup的宽高
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec); // 测量所有子View的宽高

        if (getChildCount() <= 0) {
            // 如果没有子View当前ViewGroup的宽高直接设置为0
            setMeasuredDimension(0, 0);
        } else if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.AT_MOST) { //
            // AT_MOST :最大值模式，当view的layout_width 或 layout_height 属性设置为 wrap_content时使用
            int measuredWidth = 0;  // 测量宽度
            int maxMeasuredHeight = 0; // 测量高度子View最大的高度

            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                measuredWidth += child.getMeasuredWidth();

                if (child.getMeasuredHeight() > maxMeasuredHeight) {
                    maxMeasuredHeight = child.getMeasuredHeight();
                }
            }
            setMeasuredDimension(measuredWidth, maxMeasuredHeight);
        } else {
            setMeasuredDimension(sizeWidth, sizeHeight); // 传入测量后的宽高
        }
    }
}
