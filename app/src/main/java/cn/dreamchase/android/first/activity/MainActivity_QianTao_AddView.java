package cn.dreamchase.android.first.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

/**
 * -布局嵌套及动态添加view
 */
public class MainActivity_QianTao_AddView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClick();
    }

    protected void onClick() {

        LinearLayout linearLayout = findViewById(R.id.ll_root_view);

        Button btn = findViewById(R.id.btn_add_view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(linearLayout);
            }
        });
    }

    protected void addView(LinearLayout linearLayout) {
        TextView textView = new TextView(this);
        textView.setText("动态添加View");
        linearLayout.addView(textView);
    }
}