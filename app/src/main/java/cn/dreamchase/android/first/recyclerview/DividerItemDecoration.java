package cn.dreamchase.android.first.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.dreamchase.android.first.R;

/**
 * -2.设置分隔线
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * RecyclerView的布局方向，默认先赋值为纵向布局
     * RecyclerView的布局可横向，也可纵向
     * 纵向和横向对应的分隔线画法不一样
     */

    private int orientation = LinearLayoutManager.VERTICAL;

    private int itemSize = 1; // item之间的分隔线的size，默认为1

    private Paint paint;   // 回值item分隔线的画笔，并设置其属性

    public DividerItemDecoration(Context context) {
        this(context, LinearLayoutManager.HORIZONTAL, R.color.teal_200);
    }


    public DividerItemDecoration(Context context, int orientation) {
        this(context, orientation, R.color.teal_200);
    }

    public DividerItemDecoration(Context context, int orientation, int color) {
        this(context, orientation, R.color.teal_200, 1);
    }

    /**
     * @param context
     * @param orientation 绘制方向
     * @param color       分隔线颜色，颜色资源id
     * @param itemSize    分隔线宽度，传入dp值就行
     */
    public DividerItemDecoration(Context context, int orientation, int color, int itemSize) {
        this.orientation = orientation;

        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }

        // 把dp换算成px
        this.itemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, itemSize, context.getResources().getDisplayMetrics());

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(context.getResources().getColor(color));

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (orientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();

        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();

        final int childSize = parent.getChildCount();

        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getBottom() + layoutParams.bottomMargin;

            final int bottom = top + itemSize;

            c.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();

        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();

        final int childSize = parent.getChildCount();

        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getRight() + layoutParams.rightMargin;

            final int right = left + itemSize;

            c.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, itemSize); // 垂直排列，底部偏移
        } else {
            outRect.set(0, 0, itemSize, 0); // 水平排列，右部偏移
        }
    }
}
