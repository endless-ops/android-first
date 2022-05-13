package cn.dreamchase.android.first.my.view.group.lp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import cn.dreamchase.android.first.R;

/**
 * -
 */
public class MyViewGroupLP extends ViewGroup {
    public MyViewGroupLP(Context context) {
        super(context);
    }

    public MyViewGroupLP(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupLP(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount(); // 获取子View的数量

        int left = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();// 获取子View的宽度
            int childHeight = child.getMeasuredHeight(); // 获取子View的高度

            if (lp.position == MyLayoutParams.POSITION_RIGHT) {
                // 当前子View显示在ViewGroup右边
                child.layout(getWidth() - childWidth,0,getWidth(),childWidth);
            }else if (lp.position == MyLayoutParams.POSITION_BOTTOM) {
                // 当前子View显示在ViewGroup底部
                child.layout(left + lp.leftMargin,getHeight() - childHeight,left + childWidth + lp.leftMargin,getHeight());
            }else {
                // 没有设置位置的view
                child.layout(left + lp.leftMargin, 0, left + childWidth + lp.leftMargin, child.getMeasuredHeight());
            }
            // 四个参数：left top right bottom
            left += childWidth + lp.leftMargin + lp.rightMargin;
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
                MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
                measuredWidth += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;  // 加上左右边距

                if (child.getMeasuredHeight() > maxMeasuredHeight) {
                    maxMeasuredHeight = child.getMeasuredHeight();
                }
            }
            setMeasuredDimension(measuredWidth, maxMeasuredHeight);
        } else {
            setMeasuredDimension(sizeWidth, sizeHeight); // 传入测量后的宽高
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }

    /**
     * -定义MyLayoutParams
     */
    public static class MyLayoutParams extends MarginLayoutParams {
        public static int POSITION_RIGHT = 1;
        public static int POSITION_BOTTOM = 2;

        public int position = -1;
        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.customLayoutLP);
            position = a.getInt(R.styleable.customLayoutLP_layout_position,position);
            a.recycle();
        }

        public MyLayoutParams(int width, int height) {
            super(width, height);
        }

        public MyLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public MyLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
