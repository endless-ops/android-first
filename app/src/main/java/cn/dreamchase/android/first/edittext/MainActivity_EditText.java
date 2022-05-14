package cn.dreamchase.android.first.edittext;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_EditText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listenerEditText();
    }

    /**
     * -EditText监听输入内容
     * addTextChangedListener
     */
    protected void listenerEditText() {
        EditText editTextPhone = findViewById(R.id.et__phone);

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("Ansen","内容改变之前调用：" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("Ansen","内容改变，可以去告诉服务器：" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("Ansen","内容改变之后调用：" + s);
            }
        });
    }

}