package cn.dreamchase.android.first.checkbox;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_CheckBox extends AppCompatActivity {

    private TextView textView;

    private String javaResult = "", phpResult = "", cResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_result);
        CheckBox javaBox = findViewById(R.id.cb_java);
        CheckBox phpBox = findViewById(R.id.cb_php);
        CheckBox cBox = findViewById(R.id.cb_c);

        javaBox.setOnCheckedChangeListener(onCheckedChangeListener);
        phpBox.setOnCheckedChangeListener(onCheckedChangeListener);
        cBox.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    /**
     * - CompoundButton 复合按钮
     */
    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.cb_java) {
                javaResult = isChecked ? "Java" : "";
            } else if (buttonView.getId() == R.id.cb_php) {
                phpResult = isChecked ? "PHP" : "";
            } else if (buttonView.getId() == R.id.cb_c) {
                cResult = isChecked ? "C" : "";
            }
            textView.setText(javaResult + " " + phpResult + " " + cResult);
        }
    };

}