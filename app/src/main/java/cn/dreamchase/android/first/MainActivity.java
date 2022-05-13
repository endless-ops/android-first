package cn.dreamchase.android.first;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * -2.在程序中设置值
     */
    protected void setValue() {
        TextView textView = findViewById(R.id.tv0);
        textView.setText("如何在程序中动态赋值");
    }

    /**
     * -3.实现多字符的动态处理
     * @param a
     */
    protected void setValue(int a) {
        TextView textView = findViewById(R.id.tv1);
        textView.setText(getString(R.string.testing,new Integer[]{11,21,31}));
    }

    /**
     * -4.TextView 显示HTML,字体颜色为红色
     * @param a
     */
    protected void setValue(String a) {
        TextView textView = findViewById(R.id.tv2);
        String html = "<font color='red'>TextView显示html,字体颜色为红色</font><br/>";
        textView.setText(Html.fromHtml(html));
    }

    /**
     * -5.给TextView设置点击事件
     * @param a
     * @param b
     */
    protected void setValue(int a,String b) {
        TextView textView = findViewById(R.id.tv3);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了TextView3",Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * -8.TextView的样式类span的使用
     * @param a
     */
    protected void setValue(int[] a) {
        TextView textView = findViewById(R.id.tv6);
        SpannableString spannableString = new SpannableString("TextView的样式类span的使用详解");

        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.RED);

        // 0 到 10 的字符设置红色背景
        spannableString.setSpan(backgroundColorSpan,1,10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    
}