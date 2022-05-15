package cn.dreamchase.android.first.dialogframent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cn.dreamchase.android.first.R;

public class MainActivity_DialogFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDialogFragment.newInstance().show(getSupportFragmentManager(), "MyDialogFragment");  // getSupportFragmentManager(); 使用其报错，原因是包不对
    }
}