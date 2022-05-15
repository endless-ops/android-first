package cn.dreamchase.android.first.radiaobutton;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_RadioButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private final RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_male) {
                Toast.makeText(MainActivity_RadioButton.this,"您的性别是男",Toast.LENGTH_LONG).show();
            }else if (checkedId == R.id.rb_female) {
                Toast.makeText(MainActivity_RadioButton.this,"您的性别是女",Toast.LENGTH_LONG).show();
            }
        }
    };

}