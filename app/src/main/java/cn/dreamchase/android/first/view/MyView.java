package cn.dreamchase.android.first.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

import cn.dreamchase.android.first.R;

/**
 * -自定义View
 */
public class MyView extends View {
    private MyThread myThread;
    private Paint paint; // 画笔
    private final RectF rectF = new RectF(150, 150, 380, 380);
    private int sweepAngle = 0; // 弧的当前度数
    private int sweepAngleAdd = 20; // 弧每次增加的速度
    private final Random random = new Random();
    private boolean running = true;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        paint = new Paint();
        paint.setTextSize(60);

        // 由于自定义属性，这里获取自定义属性的值
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.customStyleView);
        sweepAngleAdd = typedArray.getInt(R.styleable.customStyleView_sweepAngleAdd, 0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("MyView", "onDraw");
        if (myThread == null) {
            myThread = new MyThread();
            myThread.start();
        } else {
            // 第一个参数是RectF 左上的X，Y坐标，右下的X，Y的坐标
            // 第二个参数是 弧形的开始角度
            // 第三个参数是 弧形的结束角度
            // 第四个参数是 true：画扇型 false：画弧线
            // 第五个参数是 画笔
            canvas.drawArc(rectF, 0, sweepAngle, true, paint);

        }
    }

    protected void logic() {
        sweepAngle += sweepAngleAdd; // 每次增加的弧度

        // 随机设置画笔的颜色
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        paint.setARGB(255, r, g, b);

        if (sweepAngle >= 360) { // 如果弧度大于 360度就从头开始
            sweepAngle = 0;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        running = false;
        super.onDetachedFromWindow();
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            while (running) {
                logic();
                postInvalidate();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获得父容器为它设置的测量模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        // EXACTLY :精确值模式，当view的layout_width 或 layout_height
        // 属性设置为具体的值或者 match_content时使用(系统会自动分配为父布局的大小)

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) (getPaddingLeft() + getPaddingRight() + rectF.width() * 2);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (getPaddingTop() + getPaddingBottom() + rectF.height() * 2);
        }
        setMeasuredDimension(width, height);// 传入测量后的宽高
    }
}
