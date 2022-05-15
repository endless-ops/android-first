package cn.dreamchase.android.first.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import cn.dreamchase.android.first.R;

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private int orientation = LinearLayoutManager.VERTICAL;

    private int itemSize = 1;

    private Paint paint;

    public DividerGridItemDecoration(Context context) {
        this(context, LinearLayoutManager.VERTICAL, R.color.purple_200);
    }

    public DividerGridItemDecoration(Context context, int orientation) {
        this(context, LinearLayoutManager.VERTICAL, R.color.purple_200);
    }

    public DividerGridItemDecoration(Context context, int orientation, int color) {
        this(context, LinearLayoutManager.VERTICAL, R.color.purple_200, 1);
    }

    public DividerGridItemDecoration(Context context, int orientation, int color, int itemSize) {
        this.orientation = orientation;

        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }

        this.itemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, itemSize, context.getResources().getDisplayMetrics());

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(context.getResources().getColor(color));
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getTop() - layoutParams.topMargin;

            final int bottom = child.getBottom() + layoutParams.bottomMargin;

            final int left = child.getRight() + layoutParams.rightMargin;

            final int right = left + itemSize;

            c.drawRect(left, top, right, bottom, paint);

        }

    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - layoutParams.leftMargin;

            final int right = child.getRight() + layoutParams.rightMargin + itemSize;

            final int top = child.getBottom() + layoutParams.bottomMargin;

            final int bottom = top + itemSize;

            c.drawRect(left, top, right, bottom, paint);
        }

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int spanCount = getSpanCount(parent);

        int childCount = parent.getAdapter().getItemCount();

        // parent.getChildAdapterPosition(view) 返回给定子视图添加到的适配器位置
        if (isLastRow(parent,parent.getChildAdapterPosition(view),spanCount,childCount)) {
            outRect.set(0,0,itemSize,0);
        }else if (isLastCol(parent,parent.getChildAdapterPosition(view),spanCount,childCount)) {
            outRect.set(0,0,0,itemSize);
        }else {
            outRect.set(0,0,itemSize,itemSize);
        }


    }

    private boolean isLastCol(RecyclerView parent, int childAdapterPosition, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((childAdapterPosition + 1) % spanCount == 0) {
                return true;
            }
        }else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();

            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((childAdapterPosition + 1) % spanCount == 0) {
                    return true;
                }
            }else {
                childCount = childCount - childCount % spanCount;
                if (childAdapterPosition >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int childAdapterPosition, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (childAdapterPosition >= childCount) {
                return true;
            }



        }else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();

            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                if (childAdapterPosition >= childCount) {
                    return true;
                }
            }else {
                if ((childAdapterPosition + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }


}
