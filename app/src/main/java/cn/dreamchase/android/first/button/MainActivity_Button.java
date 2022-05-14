package cn.dreamchase.android.first.button;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_Button extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1();
    }

    /**
     * -button点击事件1
     */
    protected void button1() {
        findViewById(R.id.btn_click_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity_Button.this, "button点击事件1", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * -或者 button点击事件1
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity_Button.this, "button点击事件1", Toast.LENGTH_LONG).show();
        }
    };


    /**
     * -button点击事件写法2
     * --
     * 按钮2：给xml中的button增加了android:onClick="click"属性，然后在该布局文件对应的Activity中实现该方法，
     * 需注意，这个方法必须符合三个条件
     * 1.方法的修饰父是public
     * 2.返回值是void类型
     * 3.只有一个参数view，这个view就是被点击的事件
     */
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_click_two:
                Toast.makeText(MainActivity_Button.this, "button点击事件2", Toast.LENGTH_LONG).show();
                break;
        }
    }

}